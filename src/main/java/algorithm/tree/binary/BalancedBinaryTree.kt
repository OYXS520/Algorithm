package algorithm.tree.binary

import study.BinodeNode

/**
 * 平衡二叉树
 * 对于其中任何一颗子节点，它的左树的深度和右树的深度之差不能超过1
 */
class BalancedBinaryTree {
    class ReturnType(
        /**是否是平衡的*/
        var isBalanced: Boolean = true,
        /**树的深度是多少*/
        var height: Int = 0
    )

    /**
     * 判断一颗数是否为平衡二叉树
     */
    fun isBBT(root: BinodeNode?): Boolean {
        return process(root).isBalanced

    }

    private fun process(root: BinodeNode?): ReturnType {
        var head = root ?: return ReturnType(true, 0)
        val leftData = process(head.left)
        val rightData = process(head.right)
        val height = Math.max(leftData.height, rightData.height) + 1
        val isBalanced = leftData.isBalanced && rightData.isBalanced && (Math.abs(leftData.height - rightData.height) < 2)
        return ReturnType(isBalanced, height)
    }
}