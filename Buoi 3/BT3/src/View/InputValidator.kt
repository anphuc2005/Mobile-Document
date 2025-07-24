package View

object InputValidator {

    fun readNonBlank(prompt: String): String
    {
        var input: String
        do {
            print(prompt)
            input = readln().trim()
            if (input.isBlank()) {
                println("Ô nhập trống, thử lại.")
            }
        } while (input.isBlank())
        return input
    }

    fun readPositiveDouble(prompt: String): Double {
        var value: Double
        do {
            print(prompt)
            value = readln()?.toDoubleOrNull() ?: 0.0
            if (value <= 0.0) {
                println("Đầu vào sai, vui lòng nhập lại")
            }
        } while (value <= 0.0)
        return value
    }

    fun readPositiveInt(prompt: String): Int {
        var value: Int
        do {
            print(prompt)
            value = readln()?.toIntOrNull() ?: 0
            if (value <= 0) {
                println("Đầu vào sai, vui lòng nhập lại")
            }
        } while (value <= 0)
        return value
    }

    fun readValidatedString(prompt: String, constantType: ConstantInput): String {

        var input: String

        val validOptions = constantType.list.joinToString(", ")

        do {

            print("$prompt ($validOptions): ")

            input = readln().trim().lowercase()

            if (!constantType.isValid(input)) {

                println("Đầu vào sai, vui lòng nhập lại")

            }

        } while (!constantType.isValid(input))

        return input

    }


}