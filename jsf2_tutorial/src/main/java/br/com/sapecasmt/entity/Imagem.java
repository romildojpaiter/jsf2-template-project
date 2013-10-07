package br.com.sapecasmt.entity;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="imagem")
@SequenceGenerator(name="imagem_sqc", sequenceName = "imagem_sqc", allocationSize = 1)
public class Imagem implements AbstractEntity {
	
	private static final int LARGURA_880 = 880; 
	private static final int LARGURA_370 = 370; 
	private static final int LARGURA_368 = 368;
	private static final int LARGURA_280 = 280;
	private static final int ALTURA_390 = 390;
	private static final int ALTURA_254 = 254;
	private static final int ALTURA_193 = 193;
	private static final int ALTURA_200 = 200;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="imagem_sqc")
	private Long idImagem;

	@Column(nullable=false)
	private byte[] data;
	
	private String descricao;
	
	

	public Long getIdImagem() {
		return idImagem;
	}


	public void setIdImagem(Long idImagem) {
		this.idImagem = idImagem;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] imagem) {
		this.data = imagem;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idImagem == null) ? 0 : idImagem.hashCode());
		result = prime * result + Arrays.hashCode(data);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		if (idImagem == null) {
			if (other.idImagem != null)
				return false;
		} else if (!idImagem.equals(other.idImagem))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}


	public StreamedContent getDbImage() {
        InputStream dbStream = new ByteArrayInputStream(this.getData()); 
		StreamedContent dbImage = new DefaultStreamedContent(dbStream, "image/jpeg");
		return dbImage;
	}
	
	public String renderedImagemSize368x193(){
		String retorno = "";
		try {
			 retorno = renderedImagem(scale(this.getData(), LARGURA_368, ALTURA_193));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String renderedImagemSize370x254(){
		String retorno = "";
		try {
			 retorno = renderedImagem(scale(this.getData(), LARGURA_370, ALTURA_254));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String renderedImagemSize280x200(){
		String retorno = "";
		try {
			 retorno = renderedImagem(scale(this.getData(), LARGURA_280, ALTURA_200));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}	
	
	public String renderedImagemSize880x390(){
		String retorno = "";
		try {
			 retorno = renderedImagem(scale(this.getData(), LARGURA_880, ALTURA_390));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	public String renderedImagem(){
		
		String sourceFile = "";
		
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			// String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			
			File folder = new File(servletContext.getRealPath("/temp"));
			if (!folder.exists()){
				folder.mkdirs();
			}

			Calendar data = GregorianCalendar.getInstance();
			String nomeArquivo = data.getTimeInMillis() + ".jpg";
			String arquivo = servletContext.getRealPath("/temp") + File.separator + nomeArquivo;
			
			criaArquivo(this.getData(), arquivo);

			sourceFile = "/temp/" + nomeArquivo;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return sourceFile;
	}
	
	public String renderedImagem(byte[] byteImagem){
		
		String sourceFile = "";
		
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			// String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			
			File folder = new File(servletContext.getRealPath("/temp"));
			if (!folder.exists()){
				folder.mkdirs();
			}

			Calendar data = GregorianCalendar.getInstance();
			String nomeArquivo = data.getTimeInMillis() + ".jpg";
			String arquivo = servletContext.getRealPath("/temp") + File.separator + nomeArquivo;
			
			criaArquivo(byteImagem, arquivo);

			sourceFile = "/temp/" + nomeArquivo;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return sourceFile;
	}
	
	private void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);


			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public byte[] scale(byte[] fileData, int width, int height) throws Exception {
    	ByteArrayInputStream in = new ByteArrayInputStream(fileData);
    	try {
    		BufferedImage img = ImageIO.read(in);
    		if(height == 0) {
    			height = (width * img.getHeight())/ img.getWidth(); 
    		}
    		if(width == 0) {
    			width = (height * img.getWidth())/ img.getHeight();
    		}
    		Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

    		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    		ImageIO.write(imageBuff, "jpg", buffer);

    		return buffer.toByteArray();
    	} catch (IOException e) {
    		throw new Exception("Erro ao redimencionar imagem!");
    	}
    }


	@Override
	public Long getId() {
		return this.idImagem;
	}
	

}
