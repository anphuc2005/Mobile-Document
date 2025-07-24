package View

enum class ConstantInput(val list: List<String>) {
    LOAIVO(listOf("vo ke o", "vo ke ngang", "vo ke caro")),
    CHATLIEUGIAY(listOf("giay trang", "giay mau")),
    KICKTHUOCVO(listOf("a4", "a5", "a6")),
    CHATLIEUBUTCHI(listOf("go", "nhua")),
    DOCUNG(listOf("hb", "2b", "3b", "4b" , "5b" , "6b" , "7b" , "8b" , "9b", "10b")),
    CHATLIEUBUTMUC(listOf("nhua", "kim loai")),
    LOAIMUC(listOf("muc dau", "muc nuoc")),
    DOMIN(listOf("0.5mm", "0.7mm", "1mm", "1.5mm", "2mm", "2.5mm", "3mm", "3.5mm", "4mm", "4.5mm", "5mm"));


    fun isValid(input: String): Boolean {
        return list.contains(input.lowercase())
    }

}
