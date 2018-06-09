package br.gov.to.secad.main.view;

import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import br.gov.to.secad.main.util.FileHelper;
import br.gov.to.secad.main.util.MessagesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

/**
 * @author wandyer.silva
 * <p>
 * Classe para tratamento de Imagens no Sistema
 */
@ManagedBean
@SessionScoped
public class ImagePortalPerfilController implements Serializable {

	private static Logger logger = LogManager.getLogger();

	@ManagedProperty(value = "#{authenticationController}")
	private AuthenticationController authenticationController;

	private String fullImageUrl = null;
	private String sessionId;

	//Foto no Perfil do Servidor
	private CroppedImage croppedImage;
	private String newImageName;
	private StreamedContent graphicCropped;
	private String nameUploadedImage = null;
	private String pathCroppedImage = null;
	private UploadedFile uploadedFile;
	private String contentType = null;

	private Boolean showAlterarImagem = false;
	private Boolean showImageCropper = false;
	private Boolean showCroppedImage = false;
	private Boolean showImageUpload = false;

	private Boolean newCroppedImage = false;
	private Boolean isNewImage = true;

	public ImagePortalPerfilController() {
	}

	public void resetValues() {
		showAlterarImagem = false;
		showImageCropper = false;
		showCroppedImage = false;
		newCroppedImage = false;
		showImageUpload = false;
		isNewImage = true;
	}

	public StreamedContent getFotoPerfil() {
		InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/img/profile-avatar.png");
		try {
			FacesContext context = FacesContext.getCurrentInstance();

			return new DefaultStreamedContent(iStream);

		} catch (NullPointerException ex) {
			logger.error(ex);
			logger.error(SpringSecurityUtil.getCPF());
			return new DefaultStreamedContent(iStream);
		}

	}

	public StreamedContent getGraphicCroppedPerfil() throws FileNotFoundException {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			String path = context.getExternalContext().getRequestParameterMap().get("path");
			File file2 = new File(path);
			InputStream input = new FileInputStream(file2);
			return new DefaultStreamedContent(input);
		}
	}

	public void processoDialogAlterarFoto() {
		resetValues();
		isNewImage = false;
	}

	public void processAlterarImagem() {
		showAlterarImagem = true;
		showImageUpload = true;
		showImageCropper = false;
		showCroppedImage = false;
		newCroppedImage = false;
		isNewImage = false;
	}

	private void createFile(String fileName, InputStream in) {
		sessionId = FileHelper.mySessionID();

		String source = FileHelper.tempResourceRealPath();

		source += sessionId + "/" + "upload" + "/";

		File createDirectory = new File(source);
		createDirectory.mkdirs();

		try {
			OutputStream out = new FileOutputStream(new File(source + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/* Upload */
	public void uploadHandler(FileUploadEvent event) throws IOException {

		sessionId = FileHelper.mySessionID();

		uploadedFile = event.getFile();
		contentType = event.getFile().getContentType();
		nameUploadedImage = event.getFile().getFileName();

		fullImageUrl = sessionId + "/upload/" + nameUploadedImage;

		String fileName = uploadedFile.getFileName();
		createFile(fileName, uploadedFile.getInputstream());

		MessagesUtil.addInfo("Por favor, recorte a Imagem.");

		showImageCropper = true;
		showImageUpload = false;
		if (isNewImage) {
			showAlterarImagem = true;
			RequestContext.getCurrentInstance().execute("PF('imageDialogWv').show();");
		}

	}

	public void cropImage() {

		sessionId = FileHelper.mySessionID();

		if (croppedImage == null) {
			return;
		}

		setNewImageName(getRandomImageName());

		String source = FileHelper.tempResourceRealPath();

		source += sessionId + "/cropped/";

		//Verificar PNG ou JPG
		String contentImage = null;

		if (contentType.equalsIgnoreCase("image/jpeg")) {
			contentImage = ".jpg";
		} else if (contentType.equalsIgnoreCase("image/png")) {
			contentImage = ".png";
		}

		pathCroppedImage = source + getNewImageName() + contentImage;

		File fileCopy = new File(source);
		fileCopy.mkdirs();

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(pathCroppedImage));
			imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
			imageOutput.close();
		} catch (Exception e) {
			return;
		}

		MessagesUtil.addInfo("Imagem anexada. Confirme para salvar.");

		showImageCropper = false;
		showCroppedImage = true;
		newCroppedImage = true;
		showImageUpload = false;
	}

	private String getRandomImageName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}

	public void processoCancelarFoto() {
		showAlterarImagem = false;
		showImageCropper = false;
		showCroppedImage = false;
	}

	/* Getters e Setters */
	public String getFullImageUrl() {
		return fullImageUrl;
	}

	public void setFullImageUrl(String fullImageUrl) {
		this.fullImageUrl = fullImageUrl;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public StreamedContent getGraphicCropped() {
		return graphicCropped;
	}

	public void setGraphicCropped(StreamedContent graphicCropped) {
		this.graphicCropped = graphicCropped;
	}

	public String getNameUploadedImage() {
		return nameUploadedImage;
	}

	public void setNameUploadedImage(String nameUploadedImage) {
		this.nameUploadedImage = nameUploadedImage;
	}

	public String getPathCroppedImage() {
		return pathCroppedImage;
	}

	public void setPathCroppedImage(String pathCroppedImage) {
		this.pathCroppedImage = pathCroppedImage;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Boolean getShowAlterarImagem() {
		return showAlterarImagem;
	}

	public void setShowAlterarImagem(Boolean showAlterarImagem) {
		this.showAlterarImagem = showAlterarImagem;
	}

	public Boolean getShowImageCropper() {
		return showImageCropper;
	}

	public void setShowImageCropper(Boolean showImageCropper) {
		this.showImageCropper = showImageCropper;
	}

	public Boolean getShowCroppedImage() {
		return showCroppedImage;
	}

	public void setShowCroppedImage(Boolean showCroppedImage) {
		this.showCroppedImage = showCroppedImage;
	}

	public Boolean getNewCroppedImage() {
		return newCroppedImage;
	}

	public void setNewCroppedImage(Boolean newCroppedImage) {
		this.newCroppedImage = newCroppedImage;
	}

	public Boolean getIsNewImage() {
		return isNewImage;
	}

	public void setIsNewImage(Boolean isNewImage) {
		this.isNewImage = isNewImage;
	}

	public Boolean getShowImageUpload() {
		return showImageUpload;
	}

	public void setShowImageUpload(Boolean showImageUpload) {
		this.showImageUpload = showImageUpload;
	}

	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}
}
