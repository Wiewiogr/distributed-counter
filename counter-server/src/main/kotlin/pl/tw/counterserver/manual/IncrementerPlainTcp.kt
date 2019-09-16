package pl.tw.counterserver.manual

import java.net.InetAddress
import java.net.Socket
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Incrementer {

    private val serverAddr = InetAddress.getByName("127.0.0.1")

    private val content = "" +
            "POST /recordIncrementation HTTP/1.1\r\n" +
            "Host: localhost:8080\r\n" +
            "User-Agent: curl/7.54.0\r\n" +
            "Accept: */*\r\n\r\n"
    private val bytesContent = content.toByteArray()


    fun increment() {
        val socket = Socket(serverAddr, 8080)
        val outputStream = socket
                .getOutputStream()

        outputStream
                .write(bytesContent)

//        val inputStream = socket.getInputStream()
//        val available = inputStream.available()
//        inputStream.readNBytes(available)
    }
}

fun main(args: Array<String>) {

    val executorService = Executors.newFixedThreadPool(30)
            .apply {
                for (i in 1..30) {
                    this.submit {
                        try {
                            val incrementer = Incrementer()
                            for (j in 1..10000) {
                                incrementer.increment()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

    executorService.shutdown()
    executorService.awaitTermination(60, TimeUnit.SECONDS)
    println("finished")
}

