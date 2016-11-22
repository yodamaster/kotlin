package java.io

public class IOException(message: String = "") : Exception(message)

public interface Closeable {
    public open fun close(): Unit
}

interface Serializable
