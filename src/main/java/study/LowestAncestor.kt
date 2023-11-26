package study

/**
 * 题吗：给定一个二叉树，和其中任意两个子节点，获取这两个子节点的
 * 最小公共父节点
 * @see [resources/LowestAncestor.drawio]
 */
class LowestAncestor {
    /**
     * 从树的最底层开始判断，每一节点的左子树和自己是否包含目标节点1和目标节点2
     * 如果包含，当前节点就是最小公共节点
     */
    fun lowestAncestor(root: BinodeNode?, p1: BinodeNode, p2: BinodeNode): BinodeNode? {
        if (root == null || root == p1 || root == p2) { //包含当前节点，有可能目标节点也是最小公共节点
            return root
        }
        val left = lowestAncestor(root.left, p1, p2)
        val right = lowestAncestor(root.right, p1, p2)
        if (left != null && right != null) {
            return root
        }

        return left ?: right
    }
}