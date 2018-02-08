package org.vaadin.easyuploads;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;

import java.io.File;
import java.util.EventObject;

/**
 * UploadField is a helper class that uses rather low level {@link Upload}
 * component in core Vaadin. Instead of implementing own {@link Receiver}
 * developer can just access the contents of the file or the {@link File} where
 * the contents was streamed to. In addition to easier access to the content,
 * UploadField also provides built in {ProgressIndicator} and displays
 * (partly) the uploaded file. What/how is displayed can be easily modified by
 * overriding {@link #updateDisplay()} method.
 * <p>
 * <p>
 * UploadField can also be used in a form to edit a property - that's where the
 * name Field comes from. Property can be of type (set with
 * {@link #setFieldType(FieldType)}, but also automatically based on property
 * data source) :
 * <ul>
 * <li>byte[] ({@link FieldType#BYTE_ARRAY}) (Example: image file saved as blob
 * in DB)
 * <li>String ({@link FieldType#UTF8_STRING}) (Example: field of type String in
 * a pojo, the text is in a text file on end users computer)
 * <li>File ({@link FieldType#FILE}) (Example: fied of type File in a pojo)
 * </ul>
 * <p>
 * Typical use cases: TODO
 * <p>
 * {@link #setStorageMode(StorageMode)} controls how upload file handles the
 * received data. {@link StorageMode#FILE} mode streams uploaded file contents
 * to a file (except possibly committed value to property), the
 * {@link StorageMode#MEMORY} mode keeps everything in memory. If the Field is
 * in {@link StorageMode#FILE} mode, the file creation can be controlled with
 * {@link #setFileFactory(FileFactory)}.
 * <p>
 * <p>
 * Limitations:
 * <ul>
 * <li>Read through modes are not supported properly.
 * <li> {@link StorageMode#FILE} does not support Buffered properly(?).
 * </ul>
 *
 * @author Matti Tahvonen
 */
@SuppressWarnings({"serial"})
public class UploadFieldFile extends UploadField implements HasValue<File> {

    public UploadFieldFile() {
        this(StorageMode.FILE);
    }

    public UploadFieldFile(StorageMode mode) {
        super(mode);
        setFieldType(FieldType.FILE);
    }

    @Override
    void setValueObject(Object object) {
        setValue((File) object);
    }

    @Override
    Object getValueObject() {
        return getValue();
    }

    @Override
    EventObject createValueChangeEvent(boolean repaintIsNotNeeded) {
        return new ValueChangeEvent(this, this, null, ! repaintIsNotNeeded);
    }

    @Override
    public void setValue(File newValue) {
        receiver.setValue(newValue);
        fireValueChange();
    }

    @Override
    public File getValue() {
        return (File) receiver.getValue();
    }

    @Override
    public void clear() {
        receiver.setValue(null);
    }

}
