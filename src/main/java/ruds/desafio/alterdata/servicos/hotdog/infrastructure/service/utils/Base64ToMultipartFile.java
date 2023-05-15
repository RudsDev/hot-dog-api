package ruds.desafio.alterdata.servicos.hotdog.infrastructure.service.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import ruds.desafio.alterdata.servicos.hotdog.core.internationalization.Translator;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.NegocioException;

public class Base64ToMultipartFile implements MultipartFile{
    private final byte[] fileContent;

    private final String extension;
    private final String contentType;
    
    public Base64ToMultipartFile(String base64, String dataUri) {
        try{
            this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new NegocioException(Translator.toLocale("imagem_invalida"));
        }
        
        this.extension = dataUri.split(";")[0].split("/")[1];
        this.contentType = dataUri.split(";")[0].split(":")[1];
    }

    public Base64ToMultipartFile(String base64, String extension, String contentType) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = extension;
        this.contentType = contentType;
    }

    public static String getDataUri(String base64) {
        final String[] base64Array = base64.split(",");
        String dataUir;
        if (base64Array.length > 1) {
            dataUir = base64Array[0];
        } else {
            dataUir = "data:image/jpg;base64";
        }

        return dataUir;
    }

    public static String getData(String base64) {
        final String[] base64Array = base64.split(",");
        String data;
        if (base64Array.length > 1) {
            data = base64Array[1];
        } else {
            data = base64Array[0];
        }

        return data;
    }

    @Override
    public String getName() {
        return "param_" + System.currentTimeMillis();
    }

    @Override
    public String getOriginalFilename() {
        return "file_" + System.currentTimeMillis() + "." + extension;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent);
        }
    }
}
