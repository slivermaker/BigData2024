object UserInputExample {
  def main(args: Array[String]): Unit = {
    println("请输入您的年龄：")
    val age = scala.io.StdIn.readLine().toInt
    println("您的年龄是：" + age)
  }
}
