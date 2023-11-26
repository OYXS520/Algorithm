package study

/**
 * 前缀树
 *
 */
class TrieTree {
    /**
     * 一个字符串类型的数组arr1，另一个字符串类型的数组arr2。arr2中有哪些字符，是arr1中
     * 出现的？请打印。arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印。arr2
     * 中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印 arr2中出现次数最大的前缀。
     */
    class TrieNode(
        /**有多少个节点通过该路径*/
        var pass: Int = 0,
        /**有多少字符串是以该节点为结尾*/
        var end: Int = 0,
        //HashMap<Char,Node> nexts
        //TreeMap<Char,Node> nexts
        /**该节点的子节点*/
        var nexts: Array<TrieNode?> = arrayOf()
    )

    class Trie(
        var root: TrieNode = TrieNode()
    ) {
        fun insert(word: String?) {
            if (word == null) {
                return
            }
            val chs = word.toCharArray()
            var node = root
            node.pass++
            var index = 0
            for (i in chs.indices) {
                index = chs[i] - 'a'
                if (node.nexts[index] == null) {
                    node.nexts[index] = TrieNode()
                }
                node = node.nexts[index]!!
                node.pass++
            }
            node.end++
        }

        fun delete(word: String?) {
            if (word == null) return
            if (search(word) != 0) { //确认树中确实加入过word，才删除
                val chs = word.toCharArray()
                var node = root
                node.pass--
                var index = 0
                for (i in chs.indices) {
                    index = chs[i] - 'a'
                    if (--node.nexts[index]!!.pass == 0) {
                        //如果遍历到的这个界节点已经没有其他节点通过了。那么直接释放这个节点和后续的节点
                        node.nexts[index] = null
                        return
                    }
                    node = node.nexts[index]!!
                }
                node.end--
            }
        }

        /**
         * 搜索node这单词之前加入过几次
         */
        private fun search(word: String?): Int {
            if (word == null) return 0
            val chs = word.toCharArray()
            var node = root
            var index = 0
            for (i in chs.indices) {
                index = chs[i] - 'a'
                if (node.nexts[index] == null) {
                    return 0
                }
                node = node.nexts[index]!!
            }
            return node.end
        }

        /**
         * 所有加入字符串中，有几个是以pre这个字符串作为前缀的
         */
        fun prefixNumber(pre: String?):Int {
            if (pre ==null ) return 0
            val chs = pre.toCharArray()
            var node = root
            var index = 0
            for (i in chs.indices) {
                index = chs[i] - 'a'
                if (node.nexts[index] == null){
                    return 0
                }
                node = node.nexts[index]!!
            }
            return node.pass

        }
    }

}