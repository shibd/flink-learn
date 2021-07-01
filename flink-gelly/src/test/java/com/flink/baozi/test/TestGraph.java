package com.flink.baozi.test;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.graph.Edge;
import org.apache.flink.graph.EdgeDirection;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.ReduceEdgesFunction;
import org.apache.flink.graph.ReduceNeighborsFunction;
import org.apache.flink.graph.Vertex;
import org.apache.flink.graph.gsa.ApplyFunction;
import org.apache.flink.graph.gsa.Neighbor;
import org.apache.flink.graph.gsa.SumFunction;
import org.apache.flink.graph.library.LabelPropagation;
import org.apache.flink.graph.pregel.ComputeFunction;
import org.apache.flink.graph.pregel.MessageCombiner;
import org.apache.flink.graph.pregel.MessageIterator;
import org.apache.flink.graph.spargel.GatherFunction;
import org.apache.flink.graph.spargel.ScatterFunction;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author baozi
 */
public class TestGraph {

    @Test
    public void testGraphAPI() throws Exception {
        // create a new vertex with a Long ID and a String value
        Vertex<String, Long> v1 = new Vertex<String, Long>("1", 1l);
        Vertex<String, Long> v2 = new Vertex<String, Long>("2", 1l);
        Vertex<String, Long> v3 = new Vertex<String, Long>("3", 1l);
        Vertex<String, Long> v4 = new Vertex<String, Long>("4", 1l);
        Vertex<String, Long> v5 = new Vertex<String, Long>("5", 1l);

        Edge<String, Double> e1 = new Edge<String, Double>("1", "2", 0.1);
        Edge<String, Double> e2 = new Edge<String, Double>("1", "3", 0.5);
        Edge<String, Double> e3 = new Edge<String, Double>("1", "4", 0.4);
        Edge<String, Double> e4 = new Edge<String, Double>("2", "4", 0.7);
        Edge<String, Double> e5 = new Edge<String, Double>("2", "5", 0.3);
        Edge<String, Double> e6 = new Edge<String, Double>("3", "4", 0.2);
        Edge<String, Double> e7 = new Edge<String, Double>("4", "5", 0.9);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Vertex<String, Long>> vertices = env.fromCollection(Arrays.asList(
                v1,
                v2,
                v3,
                v4,
                v5));
        DataSet<Edge<String, Double>> edges = env.fromCollection(Arrays.asList(
                e1,
                e2,
                e3,
                e4,
                e5,
                e6,
                e7));
        Graph<String, Long, Double> graph = Graph.fromDataSet(vertices, edges, env);

        DataSet<Tuple2<String, Double>> tuple2DataSet = graph.reduceOnEdges(new ReduceEdgesFunction<Double>() {
            @Override
            public Double reduceEdges(Double firstEdgeValue, Double secondEdgeValue) {
                return firstEdgeValue + secondEdgeValue;
            }
        }, EdgeDirection.OUT);
        System.out.println(tuple2DataSet.collect());

        DataSet<Tuple2<String, Long>> tuple2DataSet1 = graph.reduceOnNeighbors(new ReduceNeighborsFunction<Long>() {
            @Override
            public Long reduceNeighbors(Long firstNeighborValue, Long secondNeighborValue) {
                return firstNeighborValue + secondNeighborValue;
            }
        }, EdgeDirection.IN);
        System.out.println(tuple2DataSet1.collect());
    }


    @Test
    public void testSingleSourceShortestPath() throws Exception {
        // create a new vertex with a Long ID and a String value
        Vertex<Long, Integer> v1 = new Vertex<Long, Integer>(1l, Integer.MAX_VALUE);
        Vertex<Long, Integer> v2 = new Vertex<Long, Integer>(2l, Integer.MAX_VALUE);
        Vertex<Long, Integer> v3 = new Vertex<Long, Integer>(3l, Integer.MAX_VALUE);
        Vertex<Long, Integer> v4 = new Vertex<Long, Integer>(4l, Integer.MAX_VALUE);

        /**
         * 1 ----> 2
         * ^ \     |
         * |  \    |
         * |   \   |
         * v     \ v
         * 3 ----> 4
         *
         */
        Edge<Long, Integer> e1 = new Edge<Long, Integer>(1l, 2l, 1);
        Edge<Long, Integer> e2 = new Edge<Long, Integer>(1l, 3l, 5);
        Edge<Long, Integer> e3 = new Edge<Long, Integer>(1l, 4l, 14);
        Edge<Long, Integer> e4 = new Edge<Long, Integer>(2l, 4l, 7);
        Edge<Long, Integer> e5 = new Edge<Long, Integer>(3l, 1l, 2);
        Edge<Long, Integer> e6 = new Edge<Long, Integer>(3l, 4l, 29);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Vertex<Long, Integer>> vertices = env.fromCollection(Arrays.asList(v1, v2, v3, v4));
        DataSet<Edge<Long, Integer>> edges = env.fromCollection(Arrays.asList(
                e1,
                e2,
                e3,
                e4,
                e5,
                e6));
        Graph<Long, Integer, Integer> graph = Graph.fromDataSet(vertices, edges, env);

        // define the maximum number of iterations
        int maxIterations = 100;

        // Execute the vertex-centric iteration
        Graph<Long, Integer, Integer> result = graph.runVertexCentricIteration(
                new SSSPComputeFunction(1l), new SSSPCombiner(), maxIterations);

        // Extract the vertices as the result
        DataSet<Vertex<Long, Integer>> singleSourceShortestPaths = result.getVertices();
        System.out.println(singleSourceShortestPaths.collect());

        System.out.println(graph.getVertices().collect());
    }

    public static final class SSSPComputeFunction extends ComputeFunction<Long, Integer, Integer, Integer> {

        private long srcId;

        public SSSPComputeFunction(long srcId) {
            this.srcId = srcId;
        }

        @Override
        public void compute(Vertex<Long, Integer> vertex, MessageIterator<Integer> messages) {

            Integer minDistance = (vertex.getId().equals(srcId)) ? 0 : Integer.MAX_VALUE;

            for (Integer msg : messages) {
                minDistance = Math.min(minDistance, msg);
            }

            if (minDistance < vertex.getValue()) {
                setNewVertexValue(minDistance);
                for (Edge<Long, Integer> e : getEdges()) {
                    System.out.println(e);
                    sendMessageTo(e.getTarget(), minDistance + e.getValue());
                }
            }
            System.out.println("------");
        }
    }

    // message combiner
    public static final class SSSPCombiner extends MessageCombiner<Long, Integer> {

        @Override
        public void combineMessages(MessageIterator<Integer> messages) {

            Integer minMessage = Integer.MAX_VALUE;
            for (Integer msg : messages) {
                minMessage = Math.min(minMessage, msg);
            }
            sendCombinedMessage(minMessage);
        }
    }


    @Test
    public void testScatterGather() throws Exception {
        // create a new vertex with a Long ID and a String value
        Vertex<Long, Integer> v1 = new Vertex<Long, Integer>(1l, 0);
        Vertex<Long, Integer> v2 = new Vertex<Long, Integer>(2l, 1000);
        Vertex<Long, Integer> v3 = new Vertex<Long, Integer>(3l, 1000);
        Vertex<Long, Integer> v4 = new Vertex<Long, Integer>(4l, 1000);

        /**
         * ⑴ --2-> ⑵
         * | \     |
         * 3  4    1
         * |   \   |
         * v     \ v
         * ⑶ -2--> ⑷
         *
         */
        Edge<Long, Integer> e1 = new Edge<Long, Integer>(1l, 2l, 2);
        Edge<Long, Integer> e2 = new Edge<Long, Integer>(1l, 3l, 3);
        Edge<Long, Integer> e3 = new Edge<Long, Integer>(1l, 4l, 4);
        Edge<Long, Integer> e4 = new Edge<Long, Integer>(2l, 4l, 1);
        Edge<Long, Integer> e5 = new Edge<Long, Integer>(3l, 4l, 2);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Vertex<Long, Integer>> vertices = env.fromCollection(Arrays.asList(v1, v2, v3, v4));
        DataSet<Edge<Long, Integer>> edges = env.fromCollection(Arrays.asList(e1, e2, e3, e4, e5));
        Graph<Long, Integer, Integer> graph = Graph.fromDataSet(vertices, edges, env);

        // define the maximum number of iterations
        int maxIterations = 100;

        Graph<Long, Integer, Integer> result = graph.runScatterGatherIteration(
                new MinDistanceMessenger(),
                new VertexDistanceUpdater(),
                maxIterations);
        System.out.println(result.getVertices().collect());

    }

    // scatter: messaging
    public static final class MinDistanceMessenger extends ScatterFunction<Long, Integer, Integer, Integer> {


        public void sendMessages(Vertex<Long, Integer> vertex) {
            for (Edge<Long, Integer> edge : getEdges()) {
                System.out.println(String.format(
                        "vertex:<%s> value:<%s> send message to <%s>:<%s>",
                        vertex.getId(),
                        vertex.getValue(),
                        edge.getTarget(),
                        vertex.getValue() + edge.getValue()));
                sendMessageTo(edge.getTarget(), vertex.getValue() + edge.getValue());
            }
        }
    }

    // gather: vertex update
    public static final class VertexDistanceUpdater extends GatherFunction<Long, Integer, Integer> {

        @Override
        public void updateVertex(
                Vertex<Long, Integer> vertex,
                org.apache.flink.graph.spargel.MessageIterator<Integer> inMessages) throws Exception {

            int minDistance = 1000;

            for (int msg : inMessages) {
                if (msg < minDistance) {
                    minDistance = msg;
                }
            }

            if (vertex.getValue() > minDistance) {
                System.out.println(String.format(
                        "--vertex <%s> updateValue<%s>",
                        vertex.getId(),
                        minDistance));
                setNewVertexValue(minDistance);
            }
        }
    }

    @Test
    public void testGatherSumApply() throws Exception {
        // create a new vertex with a Long ID and a String value
        Vertex<Long, Integer> v1 = new Vertex<Long, Integer>(1l, 0);
        Vertex<Long, Integer> v2 = new Vertex<Long, Integer>(2l, 1000);
        Vertex<Long, Integer> v3 = new Vertex<Long, Integer>(3l, 1000);
        Vertex<Long, Integer> v4 = new Vertex<Long, Integer>(4l, 1000);

        /**
         * ⑴ --2-> ⑵
         * | \     |
         * 3  4    1
         * |   \   |
         * v     \ v
         * ⑶ -2--> ⑷
         *
         */
        Edge<Long, Integer> e1 = new Edge<Long, Integer>(1l, 2l, 2);
        Edge<Long, Integer> e2 = new Edge<Long, Integer>(1l, 3l, 3);
        Edge<Long, Integer> e3 = new Edge<Long, Integer>(1l, 4l, 4);
        Edge<Long, Integer> e4 = new Edge<Long, Integer>(2l, 4l, 1);
        Edge<Long, Integer> e5 = new Edge<Long, Integer>(3l, 4l, 2);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Vertex<Long, Integer>> vertices = env.fromCollection(Arrays.asList(v1, v2, v3, v4));
        DataSet<Edge<Long, Integer>> edges = env.fromCollection(Arrays.asList(e1, e2, e3, e4, e5));
        Graph<Long, Integer, Integer> graph = Graph.fromDataSet(vertices, edges, env);

        // define the maximum number of iterations
        int maxIterations = 100;

        Graph<Long, Integer, Integer> result = graph.runGatherSumApplyIteration(
                new CalculateDistances(),
                new ChooseMinDistance(),
                new UpdateDistance(),
                maxIterations);


        System.out.println(result.getVertices().collect());

    }

    // Gather
    private static final class CalculateDistances extends org.apache.flink.graph.gsa.GatherFunction<Integer, Integer, Integer> {

        public Integer gather(Neighbor<Integer, Integer> neighbor) {
            return neighbor.getNeighborValue() + neighbor.getEdgeValue();
        }
    }

    // Sum
    private static final class ChooseMinDistance extends SumFunction<Integer, Integer, Integer> {

        public Integer sum(Integer newValue, Integer currentValue) {
            return Math.min(newValue, currentValue);
        }
    }

    // Apply
    private static final class UpdateDistance extends ApplyFunction<Long, Integer, Integer> {

        public void apply(Integer newDistance, Integer oldDistance) {
            if (newDistance < oldDistance) {
                setResult(newDistance);
            }
        }
    }

    @Test
    public void testLibraryMethods() throws Exception {
        // create a new vertex with a Long ID and a String value
        Vertex<Long, Integer> v1 = new Vertex<Long, Integer>(1l, 0);
        Vertex<Long, Integer> v2 = new Vertex<Long, Integer>(2l, 1000);
        Vertex<Long, Integer> v3 = new Vertex<Long, Integer>(3l, 1000);
        Vertex<Long, Integer> v4 = new Vertex<Long, Integer>(4l, 1000);

        /**
         * ⑴ --2-> ⑵
         * | \     |
         * 3  4    1
         * |   \   |
         * v     \ v
         * ⑶ -2--> ⑷
         *
         */
        Edge<Long, Integer> e1 = new Edge<Long, Integer>(1l, 2l, 2);
        Edge<Long, Integer> e2 = new Edge<Long, Integer>(1l, 3l, 3);
        Edge<Long, Integer> e3 = new Edge<Long, Integer>(1l, 4l, 4);
        Edge<Long, Integer> e4 = new Edge<Long, Integer>(2l, 4l, 1);
        Edge<Long, Integer> e5 = new Edge<Long, Integer>(3l, 4l, 2);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Vertex<Long, Integer>> vertices = env.fromCollection(Arrays.asList(v1, v2, v3, v4));
        DataSet<Edge<Long, Integer>> edges = env.fromCollection(Arrays.asList(e1, e2, e3, e4, e5));
        Graph<Long, Integer, Integer> graph = Graph.fromDataSet(vertices, edges, env);

        DataSet<Vertex<Long, Integer>> result = graph.run(new LabelPropagation<>(30));

        result.print();

    }


}
