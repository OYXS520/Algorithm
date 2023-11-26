package algorithm.tree.binary

import study.BinodeNode
import java.util.*

/**
 * 二叉树
 */
class BinaryTree {
    fun recursiveTraversal(root: BinodeNode?) {
        if (root == null) {
            return
        }
        //1⃣️ 先序遍历
        recursiveTraversal(root.left)
        //2⃣️ 中序遍历
        recursiveTraversal(root.right)
        //3⃣️ 后序遍历
    }

    //不使用递归先序遍历
    fun preOrderUpRecur(root: BinodeNode?) {
        var head = root
        if (head != null) {
            val stack = Stack<BinodeNode?>() //后进先出
            stack.add(head)

            while (stack.isNotEmpty()) {
                head = stack.pop()
                println(head?.value.toString() + " ")
                if (head?.right != null) {
                    stack.push(head.right)
                }
                if (head?.left != null) {
                    stack.push(head.left)
                }
            }
        }
    }

    //不使用递归的中序遍历
    fun inOrderUnRecur(root: BinodeNode?) {
        var head = root
        if (head != null) {
            val stack = Stack<BinodeNode?>()
            while (stack.isNotEmpty() || head != null) {
                if (head != null) { //把左边全部推进栈
                    stack.push(head) //先把自己
                    head = head.left //在把自己指向左边
                } else {//栈顶节点没有左子节点
                    head = stack.pop()  //取出栈顶元素
                    println(head?.value.toString() + "")
                    head = head?.right //
                }
            }
        }
    }

    //不使用递归完成后续遍历
    fun posOrderUnRecur1(root: BinodeNode?) {
        var head = root
        if (head != null) {
            val s1 = Stack<BinodeNode?>()
            val s2 = Stack<BinodeNode?>()
            s1.push(head)
            while (s1.isNotEmpty()) {
                head = s1.pop()
                s2.push(head)
                if (head?.left != null) {
                    s1.push(head.left)
                }
                if (head?.right != null) {
                    s1.push(head.right)
                }
            }
            while (s2.isNotEmpty()) {
                println(s2.pop().toString() + "")
            }
        }
    }

    //深度优先遍历，对于二叉树，深度优先遍历就是先序遍历
    //宽度优先遍历
    fun breadthFirstTraverse(root: BinodeNode?) {
        var head: BinodeNode? = root ?: return
        val queue = LinkedList<BinodeNode?>()
        queue.add(head)
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            println(cur?.value.toString() + "")
            if (cur?.left != null) {
                queue.add(cur.left)
            }
            if (cur?.right != null) {
                queue.add(cur.right)
            }
        }
    }


    /**
     * morris遍历
     */
    fun morris(root: BinodeNode?) {
        if (root == null) {
            return
        }
        var cur = root
        var mostRight: BinodeNode? = null
        while (cur != null) {
            mostRight = cur.left //mostRight是cur左孩子
            if (mostRight != null) { //有左子树
                while (mostRight!!.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right
                }
                //mostRight变成了cur左子树上，最右边的节点
                if (mostRight.right == null) { //这是第一次来到cur
                    mostRight.right = cur //把最右侧的节点与当前节点连接，因为最有边遍历完了，就轮到当前节点了
                    cur = cur.left
                    continue
                } else { //mostRight.right == cur
                    mostRight.right = null
                }
            }
            cur = cur.right
        }
    }

    /**
     * morris先序遍历
     */
    fun morrisPre(root: BinodeNode?) {
        if (root == null) {
            return
        }
        var cur = root
        var mostRight: BinodeNode? = null
        while (cur != null) {
            mostRight = cur.left //mostRight是cur左孩子
            if (mostRight != null) { //有左子树
                while (mostRight!!.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right
                }
                //mostRight变成了cur左子树上，最右边的节点
                if (mostRight.right == null) { //这是第一次来到cur
                    println(cur.value) //先序
                    mostRight.right = cur //把最右侧的节点与当前节点连接，因为最有边遍历完了，就轮到当前节点了
                    cur = cur.left
                    continue
                } else { //mostRight.right == cur
                    mostRight.right = null
                }
            } else { //没有左子树的情况
                println(cur.value) //先序
            }
            cur = cur.right
        }
    }

    /**
     * morris中序遍历
     */
    fun morrisIn(root: BinodeNode?) {
        if (root == null) {
            return
        }
        var cur = root
        var mostRight: BinodeNode? = null
        while (cur != null) {
            mostRight = cur.left //mostRight是cur左孩子
            if (mostRight != null) { //有左子树
                while (mostRight!!.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right
                }
                //mostRight变成了cur左子树上，最右边的节点
                if (mostRight.right == null) { //这是第一次来到cur
                    mostRight.right = cur //把最右侧的节点与当前节点连接，因为最有边遍历完了，就轮到当前节点了
                    cur = cur.left
                    continue
                } else { //mostRight.right == cur
                    mostRight.right = null
                }
            }
            println(cur.value) //中序遍历
            cur = cur.right
        }
    }

    /**
     * morris后序遍历
     */
    fun morrisPos(root: BinodeNode?) {
        if (root == null) {
            return
        }
        var cur = root
        var mostRight: BinodeNode? = null
        while (cur != null) {
            mostRight = cur.left //mostRight是cur左孩子
            if (mostRight != null) { //有左子树
                while (mostRight!!.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right
                }
                //mostRight变成了cur左子树上，最右边的节点
                if (mostRight.right == null) { //这是第一次来到cur
                    mostRight.right = cur //把最右侧的节点与当前节点连接，因为最有边遍历完了，就轮到当前节点了
                    cur = cur.left
                    continue
                } else { //mostRight.right == cur
                    mostRight.right = null
                    printEdge(cur.left)
                }
            }
            cur = cur.right
        }
        printEdge(root)
        println()
    }

    //以x为头的树，逆序打印这颗树的右边界
    fun printEdge(x: BinodeNode?) {
        var tail = reverseEdge(x)
        var cur: BinodeNode? = tail
        while (cur != null) {
            println(cur.value.toString() + "")
            cur = cur.right
        }
        reverseEdge(tail)
    }

    fun reverseEdge(from: BinodeNode?): BinodeNode? {
        var f = from
        var pre: BinodeNode? = null
        var next: BinodeNode? = null
        while (f != null) {
            next = f.right
            f.right = pre
            pre = f
            f = next
        }
        return pre
    }

    /**
     * 二叉树的序列化
     */
    fun serialize(input: BinodeNode?): String {

        return ""
    }

    /**
     * 二叉树的反序列化
     */
    fun deserialize(input: String): BinodeNode? {
        return null
    }

}