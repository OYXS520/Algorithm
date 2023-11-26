package study

/**
 * 查找一颗二叉树任意一个节点的的后继节点
 * 后继节点：按照中序遍历的顺序，处于当前节点后面的一个节点就是当前节点的后继节点
 */
class ParentBinaryTree {
    class ParentBinary(val value: Int) {
        var left: ParentBinary? = null
        var right: ParentBinary? = null
        var parent: ParentBinary? = null
    }

    fun getSuccessorNode(root: ParentBinary?): ParentBinary? {
        var node = root ?: return null
        return if (node.right != null) {//当前节点有右子树，那么去找右子树最左边的节点，就是当前节点的后继节点
            getLeftMst(node.right)
        }else{
            var parent = node.parent
            //如果当前节点没有右子树，并且当前节点是一个左节点，那么他的后继节点就是他的父节点
            //如果当前节点没有右子树，并且当前节点是一个右节点，那么当前节点就是其后继界节点的左子树的最右节点
            while (parent != null && parent.left != node){ //当前节点是其父节点的右孩子
                node = parent
                parent = node.parent
            }
            parent
        }
    }

    private fun getLeftMst(root: ParentBinary?): ParentBinary? {
        var head: ParentBinary? = root ?: return root
        while (head?.left != null) {
            head = head.left
        }
        return head
    }

}