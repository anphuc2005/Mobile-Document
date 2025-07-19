val contactList = mutableListOf<Contact>()

fun add()
{
    println("Nhập tên: ")
    val name = readln()
    println("Nhập SĐT: ")
    val phoneNumber = readln()
    val contact = Contact(name, phoneNumber)
    contactList.add(contact)
    println("Danh bạ đã tạo thành công")
}
fun view()
{
    if(contactList.isEmpty())
    {
        println("Danh bạ trống")
    }
    else
    {
        println("Danh bạ:")
        for((index, value) in contactList.withIndex())
        {
            println("Danh bạ sô ${index + 1} là: ${value.name} | ${value.phoneNumber}")
        }
    }
}
fun edit()
{
    view()
    if(contactList.isEmpty()) return;
    println("Nhập số thứ tự muốn sửa: ")
    val index = readln().toIntOrNull()?.minus(1)
    if(index == null || index < 0)
    {
        println("Số thứ tự không hợp lệ")
        return
    }
    try{
        val contact = contactList[index]
        println("Nhập tên mới: ")
        val newName = readln()
        println("Nhập SĐT mới: ")
        val newPhoneNumber = readln()

        contact.name = newName
        contact.phoneNumber = newPhoneNumber

        println("Danh bạ đã được cập nhật")
        view()

    }
    catch (e: IndexOutOfBoundsException)
    {
        println("Không tìm thấy liên hệ với STT đó")
    }
}
fun delete()
{
    view()
    if (contactList.isEmpty()) return

    print("Nhập số thứ tự liên hệ muốn xoá: ")
    val index = readln().toIntOrNull()?.minus(1)

    if (index == null || index < 0) {
        println("Số thứ tự không hợp lệ")
        return
    }

    try{
        val removed = contactList.removeAt(index)
        println("Đã xoá liên hệ: ${removed.name}")
        view()
    }
    catch (e: IndexOutOfBoundsException)
    {
        println("Không tìm thấy liên hệ với STT đó")
    }
}