package algorithm.tree.binary

import study.BinodeNode
import java.util.*

/**
 * 完全二叉树
 * 节点按照从上到下从左到右依次排列不存在跳过的
 */
class CompleteBinaryTree {

    /**
     * 判断一颗数是否为完全二叉树
     */
    fun isCBT(root: BinodeNode?): Boolean {
        var head: BinodeNode? = root ?: return true
        val queue = LinkedList<BinodeNode?>()
        var leaf = false //是否遇到过左右子节点不双全的情况
        var l: BinodeNode? = null
        var r: BinodeNode? = null
        queue.add(head)
        while (queue.isNotEmpty()) {
            head = queue.poll()
            l = head?.left
            r = head?.right
            if (
                (leaf && (l != null || r != null)) // 如果遇到过缺失字节点的节点，当前节点不是叶子节点，直接false
                ||
                (l == null && r != null) //有右无左 直接false
            ) {
                return false
            }
            if (l != null) {
                queue.add(l)
            }
            if (r != null) {
                queue.add(r)
            }
            if (l == null || r == null) { //遇到了缺失子节点的节点
                leaf = true
            }
        }
        return true
    }
}