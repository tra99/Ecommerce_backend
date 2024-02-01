package project.ip.ecommerce.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    
    public static byte[] compressImage(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] tmp=new byte[100*1024];
        while (!deflater.finished()) {
            int size=deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater= new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] tmp=new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count =inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return outputStream.toByteArray();
    }
}

// package project.ip.ecommerce.util;

// import java.io.ByteArrayOutputStream;
// import java.io.IOException; // Correct import for IOException
// import java.io.InputStream;
// import java.util.zip.DataFormatException; // Missing import for DataFormatException
// import java.util.zip.Deflater;
// import java.util.zip.Inflater;
// import java.nio.file.*;


// import org.springframework.web.multipart.MultipartFile;

// public class ImageUtils {

//     public static byte[] compressImage(byte[] data) {
//         Deflater deflater = new Deflater();
//         deflater.setLevel(Deflater.BEST_COMPRESSION);
//         deflater.setInput(data);
//         deflater.finish();

//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//         byte[] tmp = new byte[4 * 1024];
//         while (!deflater.finished()) {
//             int size = deflater.deflate(tmp);
//             outputStream.write(tmp, 0, size);
//         }
//         try {
//             outputStream.close();
//         } catch (IOException e) {
//             e.printStackTrace(); // Handle the exception properly
//         }
//         return outputStream.toByteArray();
//     }

//     public static byte[] decompressImage(byte[] data) {
//         Inflater inflater = new Inflater();
//         inflater.setInput(data);
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//         byte[] tmp = new byte[4 * 1024];
//         try {
//             while (!inflater.finished()) {
//                 int count = inflater.inflate(tmp);
//                 outputStream.write(tmp, 0, count);
//             }
//             outputStream.close();
//         } catch (IOException | DataFormatException e) {
//             e.printStackTrace(); // Handle the exception properly
//         }
//         return outputStream.toByteArray();
//     }

//     public String uploadImage(MultipartFile file, String productId) throws IOException {
//         try {
//             // Get the InputStream from the MultipartFile
//             InputStream inputStream = file.getInputStream();

//             // Read the input stream into a byte array
//             byte[] imageData = inputStream.readAllBytes();

//             // Compress the image data
//             byte[] compressedImage = ImageUtils.compressImage(imageData);

//             // Your logic for storing the compressedImage and productId

//             return "Image uploaded successfully";
//         } catch (IOException e) {
//             // Handle IO exception
//             throw new IOException("Failed to upload image: " + e.getMessage());
//         }
//     }

//     public byte[] downloadImage(String fileName) throws IOException {
//         // Your logic for retrieving compressed image data based on fileName
//         Path imagePath = Paths.get("path/to/your/images/directory/" + fileName);
    
//         try {
//             byte[] compressedImageData = Files.readAllBytes(imagePath);
//             return ImageUtils.decompressImage(compressedImageData);
//         } catch (IOException e) {
//             throw new IOException("Failed to download image: " + e.getMessage());
//         }
//     }
    

//     public void processImage(MultipartFile file) {
//         try {
//             // Get the InputStream from the MultipartFile
//             InputStream inputStream = file.getInputStream();

//             // Read the input stream into a byte array
//             byte[] imageData = inputStream.readAllBytes();

//             // Simulate image processing (you can replace this with your actual processing logic)
//             // For example, you might resize the image, apply filters, etc.
//             // Here, we'll simply compress the image again as an example
//             byte[] processedImage = ImageUtils.compressImage(imageData);

//             // Your logic for further processing or storing the processed image
//             // For now, let's just print the length of the processed image
//             System.out.println("Processed Image Length: " + processedImage.length);
//         } catch (IOException e) {
//             // Handle IO exception
//             e.printStackTrace(); // or log the exception
//         }
//     }
// }

