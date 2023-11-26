package test

object RandomArray {
    fun generateRandomArray(maxSize:Int, maxValue:Int):IntArray{
        val intArray = IntArray(((maxSize + 1) * Math.random()).toInt())
        for (index in intArray.indices) {
            intArray[index] = ((maxValue + 1) * Math.random()).toInt() - (maxValue * Math.random()).toInt()
        }
        return intArray
    }
}