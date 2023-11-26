package leetcode

import java.util.ArrayDeque

/**
 *
 */
class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}
//BFS 广度优先算法
class Solution1 {
    fun connect(root: Node?): Node? {
        if (root == null) {
            return null
        }
        val queue = ArrayDeque<Node>() //存放每一层的所有节点，按照顺序存放
        queue.offer(root) //尾插
        while (!queue.isEmpty()) {
            val size = queue.size
            var last: Node? = null //保存上一个节点
            for (i in 1..size) { //根据当前层的节点数，把下一层的节点全部添加到队列中
                val f = queue.pop()
                if (f.left != null){
                    queue.offer(f.left)
                }
                if (f.right != null){
                    queue.offer(f.right)
                }
                if ( i != 1){ //每一层第一个节点没有上一个节点
                    last?.next = f //上一个节点链接下一个节点
                }
                last = f //保存上一个节点
            }
        }
        return root
    }
}
//DFS 广度优先算法
class Solution2 {
    private val layers = arrayListOf<Node>()
    fun connect(root: Node?): Node? {
        dfs(root,0)
        return root
    }

    private fun dfs(node: Node?, depth: Int) {
        if (node == null){
            return
        }
        if (depth == layers.size){
            layers.add(node)
        }else{
            layers[depth].next = node
            layers[depth] = node
        }
        dfs(node.left,depth + 1)
        dfs(node.right,depth + 1)
    }
}

fun test117(){
    val root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left!!.left = Node(4)
    root.left!!.right = Node(5)
    root.right!!.right = Node(7)
    Solution1().connect(root)
}