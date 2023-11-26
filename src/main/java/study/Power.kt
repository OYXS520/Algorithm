package study
//判断一个32位正数是不是2的幂，4的幂
class Power {
    fun is2Power(n: Int): Boolean {
        return n and n - 1 != 0
    }

    //0x55555555即为0101010...1010101
    fun is4Power(n: Int): Boolean {
        return n and n - 1 != 0 && n and 0x55555555 != 0
    }
}