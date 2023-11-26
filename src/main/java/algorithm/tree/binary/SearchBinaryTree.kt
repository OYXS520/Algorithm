package algorithm.tree.binary

import study.BinodeNode

/**
 * 搜索二叉树
 * 若它的左子树不为空，左子树上所有节点的值都小于它的根节点。
 * 若它的右子树不为空，右子树上所有的节点的值都大于它的根节点。
 */
class SearchBinaryTree {
    class ReturnType(
        /**是否是平衡的*/
        var isSearch: Boolean = true,
        /**当前树的最大值是*/
        var max: Int = 0,
        /**当前树的最小值是*/
        var min: Int = 0
    )

    fun isSBT(root: BinodeNode?): Boolean {
        return process(root)?.isSearch ?: true
    }

    fun process(root: BinodeNode?): ReturnType? {
        var head = root ?: return null
        val leftData = process(head.left)
        val rightData = process(head.right)
        var max = head.value
        var min = head.value
        if (leftData != null) {
            min = Math.min(min, leftData.min) //获取左子树的最小值
            max = Math.max(max, leftData.max) //获取左子树的最大值
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min) //获取右子树的最小值
            max = Math.max(max, rightData.max) //获取右子树的最大值
        }
        var isBst = true
        if (leftData != null &&
            (
                !leftData.isSearch
                ||
                leftData.max > head.value
            )
        ) {
            isBst = false
        }
        if (rightData != null &&
            (
                !rightData.isSearch
                ||
                rightData.min < head.value
            )
        ) {
            isBst = false
        }
        return ReturnType(isBst, max, min)
    }
}