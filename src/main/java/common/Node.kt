package study

class Node(val value: Int) {
    var next: Node? = null
}

class BinodeNode(val value: Int) {
    var left: BinodeNode? = null
    var right: BinodeNode? = null
}

/**
 * 图的边
 */
class GraphEdge(
    /**边的权重*/
    val weight: Int,
    /**从那个节点出来*/
    var from: GraphNode,
    /**到哪个节点去*/
    var to: GraphNode
)

/**
 * 图的节点
 */
class GraphNode(
    val value: Int,
    /**有多少节点指向自己*/
    var `in`: Int = 0,

    /**自己指向多少节点*/
    var out: Int = 0,

    /**自己指向的节点有哪些*/
    val nexts: ArrayList<GraphNode> = arrayListOf<GraphNode>(),

    /**属于自己的边有哪些，由自己发射出的边*/
    val edges: ArrayList<GraphEdge> = arrayListOf<GraphEdge>()
)

/**
 * 图
 */
class Graph(
    /**图的节点的集合*/
    var nodes: HashMap<Int, GraphNode> = hashMapOf(),
    /**图的边的集合*/
    var edges: HashSet<GraphEdge>
)