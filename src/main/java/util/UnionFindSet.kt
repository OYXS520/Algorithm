package util

import study.GraphNode
import java.util.*
import kotlin.collections.HashSet

class UnionFindSet<V>(list: List<V>) {
    class Element<V>(var value: V)

    val elementMap = hashMapOf<V, Element<V>>() //元素对应包装元素
    val fatherMap = hashMapOf<Element<V>, Element<V>>() //包装元素的父元素
    val sizeMap = hashMapOf<Element<V>, Int>() //最顶的父元素的代表集合的大小

    init {
        for (value in list) {
            val element = Element(value)
            elementMap.put(value, element)
            fatherMap.put(element, element)
            sizeMap.put(element, 1)
        }
    }

    //查找某一个元素的父
    fun findHead(ele: Element<V>): Element<V> {
        var element = ele
        val path = Stack<Element<V>>()
        while (element != fatherMap.get(element)) {
            path.push(element)
            element = fatherMap.get(element)!!
        }
        while (path.isNotEmpty()) { //扁平化处理
            fatherMap.put(path.pop(), element)
        }
        return element
    }
    //是否是同一个集合
    fun isSameSet(a: V, b: V): Boolean {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            return findHead(elementMap.get(a)!!) == findHead(elementMap.get(b)!!)
        }
        return false
    }
    //合并两个集合
    fun union(a: V, b: V) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            val af = findHead(elementMap.get(a)!!)
            val bf = findHead(elementMap.get(b)!!)
            if (af != bf) {

                val big: Element<V> = if (sizeMap.get(af)!! >= sizeMap.get(bf)!!) {
                    af
                } else {
                    bf
                }
                val small: Element<V> = if (big == af) {
                    bf
                } else {
                    af
                }
                fatherMap.put(small, big)
                sizeMap.put(big, sizeMap.get(af)!! + sizeMap.get(bf)!!)
                sizeMap.remove(small)
            }
        }
    }
}

/**
 * 简单的并查集合
 */
class SimpleUnionFindSet(nodes: List<GraphNode>) {
    val setMap = hashMapOf<GraphNode, HashSet<GraphNode>>()

    init {
        for (cur in nodes) { //给每个节点创建一个集合，并把集合作为value，节点作为key放到map中
            setMap[cur] = hashSetOf(cur) //{Node1 -> [Node1,],}
        }
    }

    /**
     * 合并两个集合
     * { key                value
     *  A,B,C       ->      [A,B,C], //from
     *  D,E         ->      [D,E] //to
     *  }
     * { key                value
     *  A,B,C,D,E   ->      [A,B,C,D,E]
     * }
     */
    fun union(from: GraphNode, to: GraphNode) {
        val fromSet = setMap[from] ?: throw IllegalStateException("from node is not exist")
        val toSet = setMap[to] ?: return
        for (toNode in toSet) {
            fromSet.add(toNode) //把toSet合并到fromSet中
            setMap[toNode] = fromSet // 并且toSet中的节点所指向的集合也改为合并后的集合
        }
    }

    /**
     * 查询两个是否在统一集合中
     */
    fun isSomeSet(from: GraphNode, to: GraphNode): Boolean {
        val fromSet = setMap[from]
        val toSet = setMap[to]
        return fromSet == toSet
    }
}