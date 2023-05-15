package ruds.desafio.alterdata.servicos.hotdog.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}

	/**
	 * Valida se o tamanho do arquivo enviado é menor ou igual ao tamanho definido
	 * em FileSize.
	 */
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		return file == null || file.getSize() <= this.maxSize.toBytes();
	}

}
