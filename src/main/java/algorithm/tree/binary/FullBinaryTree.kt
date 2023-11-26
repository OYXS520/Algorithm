package algorithm.tree.binary

import study.BinodeNode

/**
 * 满二叉树
 * 任意一颗子树都满足 树的高度 ^2 - 1 == 子树的节点个数
 */
class FullBinaryTree {
    class ReturnType(
        /**树的高度*/
        var height: Int,
        /**树的节点个数*/
        var mode: Int
    )

    /**
     * 判断一颗数是否是满二叉树
     * 方法1：判断节点深度，和节点个数的关系，节点个数 = 节点深度 ^2 - 1
     */
    fun isFBT(root: BinodeNode?): Boolean {

        val process = process(root)
        return process.mode == (1 shl process.height - 1)
    }

    private fun process(root: BinodeNode?): ReturnType {
        val head = root ?: return ReturnType(0, 0)
        val leftData = process(head.left)
        val rightData = process(head.right)
        val height = Math.max(leftData.height, rightData.height) + 1 //当前树高为子树的高 + 1
        val mode = Math.max(leftData.mode, rightData.mode) + 1 //
        return ReturnType(height, mode)
    }
}