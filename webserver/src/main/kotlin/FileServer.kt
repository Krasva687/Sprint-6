import ru.sber.filesystem.VFilesystem
import ru.sber.filesystem.VPath
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket


class FileServer {

    @Throws(IOException::class)
    fun run(socket: ServerSocket, fs: VFilesystem) {

        while (true) {
            socket.accept().use {
                handleAccept(it, fs)
            }
        }
    }
    private fun handleAccept(socket: Socket, fs: VFilesystem) {
        val read = socket.getInputStream().bufferedReader()
        val splitRequest = read.readLine().split("\\s+".toRegex())
        val fileText = fs.readFile(VPath(splitRequest[1]))
        val answer: String
        if (fileText.isNullOrEmpty()){
            answer = "HTTP/1.0 404 Not Found\r\nServer: FileServer\r\n\r\n"
        }
        else{
            answer = "HTTP/1.0 200 OK\r\nServer: FileServer\r\n\r\n" + fileText
        }
        //println(answer)
        val write = PrintWriter(socket.getOutputStream())
        write.print(answer)
        write.close()
    }
}