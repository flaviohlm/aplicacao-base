package br.gov.to.secad.main.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.UploadedFile;

import javax.faces.context.FacesContext;
import java.io.*;

public class DownloadUploadUtil {

    private static Logger logger = LogManager.getLogger();

    public static void createFileTemp(byte[] file, String fileName) {
        if (file != null && file.length > 0) {
            InputStream stream = new ByteArrayInputStream(file);
            String source = FileHelper.tempResourceRealPath();
            source += FileHelper.mySessionID() + "/";
            File createDirectory = new File(source);
            createDirectory.mkdirs();

            try {
                OutputStream out = new FileOutputStream(new File(source + fileName));
                int read;
                byte[] bytes = new byte[1024];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                stream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    public static byte[] findFileSource(String fileSource) {
        try {
            File file = new File(fileSource);
            byte[] bFile = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bFile);
            fis.close();
            return bFile;
        } catch (IOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public static void downloadRedirectFile(String fileName) throws IOException {
        String redirectUrl = FileHelper.tempResourceRequestPath() + FileHelper.mySessionID() + "/" + fileName;
        FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
    }

    public static byte[] uploadStream(UploadedFile uploadedFile) {
        try {
            InputStream is = uploadedFile.getInputstream();
            byte[] file = new byte[(int) uploadedFile.getSize()];
            int offset = 0;
            int numRead;
            while (offset < file.length && (numRead = is.read(file, offset, file.length - offset)) >= 0) {
                offset += numRead;
            }
            return file;
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    public static String cleanFileName(String fileName) {
        return StringUtil.removeAcentos(fileName);
    }

    public static String removeExtension(String fileName) {
        return FilenameUtils.removeExtension(StringUtil.removeAcentos(fileName));
    }

    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }
}
