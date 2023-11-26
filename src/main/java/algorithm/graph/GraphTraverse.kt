package algorithm.graph

import study.Graph
import study.GraphEdge
import study.GraphNode
import util.SimpleUnionFindSet
import java.util.*


/**
 * 图的遍历
 */
class GraphTraverse {
    /**
     * 题目：图的遍历
     * 算法：宽度优先遍历
     */
    fun bfs(node: GraphNode?) {
        if (node == null) return
        val queue = LinkedList<GraphNode>()
        val set = HashSet<GraphNode>() //注册已经添加过的节点，避免重复遍历
        queue.add(node)
        set.add(node)
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            println(cur.value) //遍历
            for (next in cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next)
                    queue.add(next)
                }
            }
        }
    }

    /**
     * 题目：图的遍历
     * 算法：深度优先遍历
     */
    fun dfs(node: GraphNode?) {
        if (node == null) return
        val stack = Stack<GraphNode>()
        val set = HashSet<GraphNode>()
        stack.add(node)
        set.add(node)
        println(node.value) //处理 第一次入栈时处理
        //如果发现某一条路没有走过，就一条路走到黑，并依次把路上的节点处理，走到无路可走时，
        //就回到上一个路口，走另一条路，同样的一直走到无路可走，再回到上一个路口
        while (stack.isNotEmpty()) {
            val cur = stack.pop() //获取当前路口
            for (next in cur.nexts) { //遍历当前路口有哪些分叉路
                if (!set.contains(next)) { //发现某一条分叉路条路还没有走过
                    stack.push(cur) //记录下当前路口
                    stack.push(next) //并告知自己走要走当前路口的某一条分叉路
                    set.add(next) //标记这个分叉已经走过
                    println(next.value) //处理 第一次入栈时处理
                    break //去跳出当前路口，去处理刚刚走到的分叉路
                }
            }
        }
    }

    /**
     * 题目：可以想一想Gredle中的依赖库，某一个库依赖其他库，其他库之前也有依赖，但是不能形成循环依赖的情况，那么要初始化当前库，
     * 就必须先把依赖库初始化，那么这个项目的库初始化的顺序应该是怎样的。
     * 算法：拓扑排序
     *
     */
    fun topologySort(graph: Graph): List<GraphNode> {
        //key:某一个node
        //value：剩余的入度
        val inMap = HashMap<GraphNode, Int>()
        //剩余入度为0的点，才能进入这个队列
        val zeroInQueue = LinkedList<GraphNode>()
        for (node in graph.nodes.values) { //遍历图的节点
            inMap.put(node, node.`in`)
            if (node.`in` == 0) {
                zeroInQueue.add(node)
            }
        }
        //拓扑排序的结果，依次加入result
        val result = arrayListOf<GraphNode>()
        while (zeroInQueue.isNotEmpty()) {
            val cur = zeroInQueue.poll()
            result.add(cur)
            for (next in cur.nexts) {
                inMap[next] = inMap[next]!! - 1
                if (inMap[next]!! == 0) {
                    zeroInQueue.add(next)
                }
            }
        }
        return result
    }

    /**
     * 题目：最小生成树，怎么把所有节点都连接成树，并且权值和最小
     * 算法：kruskal算法
     * 无向有权图，怎么把所有节点都连接成树，并且权值和最小
     */
    //K算法，从权值小的边开始遍历所有边，如果没有把节点 形成环 ，就需要这条边组成树，否则就不需要这条边
    //知道所有边都遍历完成，就得到了最小生成树
    //判断是否形成环，可以使用并查集
    fun kruskalMST(graph: Graph): Set<GraphEdge> {
        val unionFindSet = SimpleUnionFindSet(graph.nodes.values.toList())
        val priorityQueue = PriorityQueue(EdgeComparator()) //排好序的边,按从小到大弹出
        for (edge in graph.edges) {
            priorityQueue.add(edge)
        }
        val result = hashSetOf<GraphEdge>()
        while (priorityQueue.isNotEmpty()) {
            val edge = priorityQueue.poll() //拿到当前最小的边
            if (!unionFindSet.isSomeSet(edge.from, edge.to)) { //不在同一个集合中，就表示没有形成环
                result.add(edge) //把当前这条边添加到目标集合中
                unionFindSet.union(edge.from, edge.to) //然后合并这两个集合
            }
        }
        return result
    }

    /**
     * 题目：最小生成树，怎么把所有节点都连接成树，并且权值和最小
     * 算法：prim算法
     * 输入数据的要求：要求无向图
     */
    fun primMST(graph: Graph): Set<GraphEdge> {
        val priorityQueue = PriorityQueue<GraphEdge>(EdgeComparator())
        val set = hashSetOf<GraphNode>() //已经处理过的节点
        val result = hashSetOf<GraphEdge>() //已经处理的节点的所有边
        for (node in graph.nodes.values) {
            if (!set.contains(node)) {//获取到一个没有处理的节点
                set.add(node) //标记为处理过
                for (edge in node.edges) { //获取当前处理的节点的所有边
                    priorityQueue.add(edge)
                }
                while (priorityQueue.isNotEmpty()) {
                    val edge = priorityQueue.poll() //获取所有已经处理节点的所有边中最小的那个边
                    val toNode = edge.to //获取最小的那个边指向的节点
                    if (!set.contains(toNode)) { //如果那个节点没有处理过
                        set.add(toNode) //设置为处理过
                        result.add(edge) //并找到了一条最小边
                        for (nextEdge in toNode.edges) { //并把新添加为处理的节点的所有边添加到处理边的集合中
                            priorityQueue.add(nextEdge)
                        }
                    }
                }
            }
        }
        return result
    }

    class EdgeComparator : Comparator<GraphEdge> {
        override fun compare(p0: GraphEdge, p1: GraphEdge): Int {
            return p0.weight - p1.weight
        }
    }

    /**
     * 题目：最小生成树，怎么把所有节点都连接成树，并且权值和最小
     * 算法：dijkstra算法
     * 输入数据的要求：要求无向图
     */
    fun dijkstraMST(head: GraphNode) {
        //从head出发到所有点的最小距离
        //key：从head出发到达key
        //value：从head出发到key的最小距离，会随着节点而更新
        //如果在表中，没有T的记录，含义是从head出发到T这个节点的距离为正无穷
        val distanceMap = hashMapOf<GraphNode, Int>()
        distanceMap[head] = 0
        //已经求过距离的节点，存在selectedNodes中，以后再也不碰
        val selectedNodes = hashSetOf<GraphNode>()
        var minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes)
        while (minNode != null) {
            val distance = distanceMap[minNode]!! //获取最小路径和的那个节点的路径和
            for (edge in minNode.edges) { //并且遍历这个节点的所有边
                val toNode = edge.to //获取边的下一个节点
                if (!distanceMap.containsKey(toNode)) { //如果最小路径的下一个节点没有被选中
                    distanceMap[toNode] = distance + edge.weight //那么新增这个节点，并且设置到达它的路径和为上一个节点到和当前的权重
                }
                //判断当前节点到下一个节点的最小路径和
                distanceMap[edge.to] = Math.min(distanceMap[toNode]!!, distance + edge.weight)
            }
            selectedNodes.add(minNode) //把当前节点最小节点设置为选中
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes) //找到下一个最小路径和的节点
        }
    }

    /**
     * 当前已经测量出路径和的节点中，找到路径和最小的那个但是不能是已经选中的节点。
     */
    private fun getMinDistanceAndUnselectedNode(distanceMap: java.util.HashMap<GraphNode, Int>, selectedNodes: java.util.HashSet<GraphNode>): GraphNode? {
        var minNode: GraphNode? = null
        var minDistance = Int.MAX_VALUE
        for (entry in distanceMap.entries) { //遍历已经记录的节点集合
            val node = entry.key //已经记录的节点
            val distance = entry.value //当前到达该节点的最小路径和
            if (
                !selectedNodes.contains(node)  //当前节点没有被找到最小值
                &&
                distance < minDistance //并且到达当前节点的路径小于
            ) { //
                minNode = node
                minDistance = distance
            }
        }
        return minNode
    }

    class NodeRecord(node: GraphNode?, distance: Int) {
        var node: GraphNode?
        var distance: Int

        init {
            this.node = node
            this.distance = distance
        }
    }

    class NodeHeap(size: Int) {
        private val nodes: Array<GraphNode?>

        //nodes记录堆的位置？如果-1表示曾经进来过
        private val heapIndexMap: HashMap<GraphNode?, Int>
        private val distanceMap: HashMap<GraphNode?, Int>
        private var size: Int
        val isEmpty: Boolean
            get() = size == 0

        fun addOrUpdateOrIgnore(node: GraphNode, distance: Int) {
            //如果node在heap中，和原先distanceMap对比取小值存入
            if (inHeap(node)) {
                distanceMap[node] = Math.min(distanceMap[node]!!, distance)
                insertHeapify(node, heapIndexMap[node]!!)
            }
            //如果node未加入heapIndexMap中，
            if (!isEntered(node)) {
                nodes[size] = node
                heapIndexMap[node] = size
                distanceMap[node] = distance
                insertHeapify(node, size++)
            }
        }

        fun pop(): NodeRecord {
            val nodeRecord = NodeRecord(nodes[0], distanceMap[nodes[0]]!!)
            //交换0和size-1的位置
            swap(0, size - 1)
            //将原本头节点的位置的heapIndexMap对应value设置为-1
            heapIndexMap[nodes[size - 1]] = -1
            //distanceMap移除该记录
            distanceMap.remove(nodes[size - 1])
            nodes[size - 1] = null
            //新的节点被交换至头节点位置
            heapify(0, --size)
            return nodeRecord
        }

        private fun insertHeapify(node: GraphNode, index: Int) {
            var index = index
            while (distanceMap[nodes[index]]!! < distanceMap[nodes[(index - 1) / 2]]!!) {
                swap(index, (index - 1) / 2)
                index = (index - 1) / 2
            }
        }

        private fun heapify(index: Int, size: Int) {
            var index = index
            var left = index * 2 + 1
            while (left < size) {
                var smallest = if (left + 1 < size && distanceMap[nodes[left + 1]]!! < distanceMap[nodes[left]]!!) left + 1 else left
                smallest = if (distanceMap[nodes[smallest]]!! < distanceMap[nodes[index]]!!) smallest else index
                if (smallest == index) {
                    break
                }
                swap(smallest, index)
                index = smallest
                left = index * 2 + 1
            }
        }

        private fun isEntered(node: GraphNode): Boolean {
            return heapIndexMap.containsKey(node)
        }

        //判断某个node是否在Heap中，
        private fun inHeap(node: GraphNode): Boolean {
            return isEntered(node) && heapIndexMap[node] !== -1
        }

        //数组在堆上的位置交换节点
        private fun swap(index1: Int, index2: Int) {
            heapIndexMap[nodes[index1]] = index2
            heapIndexMap[nodes[index2]] = index1
            val tmp: GraphNode? = nodes[index1]
            nodes[index1] = nodes[index2]
            nodes[index2] = tmp
        }

        init {
            nodes = arrayOfNulls<GraphNode>(size)
            heapIndexMap = HashMap()
            distanceMap = HashMap()
            this.size = 0
        }
    }

    //迪杰斯特拉算法优化
    fun dijkstra2(head: GraphNode, size: Int): HashMap<GraphNode?, Int>? {
        val nodeHeap = NodeHeap(size)
        nodeHeap.addOrUpdateOrIgnore(head, 0)
        val result: HashMap<GraphNode?, Int> = HashMap()
        while (!nodeHeap.isEmpty) {
            val record = nodeHeap.pop()
            val cur: GraphNode = record.node!!
            val distance = record.distance
            for (edge in cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance)
            }
            result[cur] = distance
        }
        return result
    }


}