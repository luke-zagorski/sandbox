package com.performgroup.interview.utils;

import com.performgroup.interview.commons.dto.DTO;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: luke
 */
public abstract class AbstractDtoMapper<V extends DTO> {

    private V dto;
    private Unmarshaller unmarshaller;

    public V getDto() {
        return dto;
    }

    public void setDto(V dto) {
        this.dto = dto;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void unmarshallXml(String filename) throws IOException {

        InputStream resourceAsStream = null;
        try {
            resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
            unmarshallXml(resourceAsStream);
        } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
    }

    public void unmarshallXml(InputStream resourceAsStream) throws IOException {

        if (resourceAsStream == null) {
            throw new RuntimeException("Config file could not be read from resources. Input stream is null.");
        }
        this.dto = (V) this.unmarshaller.unmarshal(new StreamSource(resourceAsStream));
    }

}
