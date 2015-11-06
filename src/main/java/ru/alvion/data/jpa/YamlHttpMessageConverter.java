package ru.alvion.data.jpa;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.web.dto.RabbitMotionEventDto;
import ru.alvion.data.jpa.web.dto.RabbitPastureEventDto;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Yaml converter
 */
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {


    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public YamlHttpMessageConverter() {
        super(new MediaType("application", "yaml", DEFAULT_CHARSET));
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        Yaml yaml = createYaml();
        Object obj = yaml.loadAs(inputMessage.getBody(), clazz);
        return obj;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return canWrite(mediaType);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // should not be called, since we override canRead/Write instead
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeInternal(Object t,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Yaml yaml = createYaml();

        String yamlString = yaml.dump(t);
        IOUtils.write(yamlString, outputMessage.getBody(), DEFAULT_CHARSET);
    }

    private Yaml createYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Representer representer = new Representer();
        representer.addClassTag(RabbitMotionEventDto.class, new Tag("!RabbitMotionEvent"));
        representer.addClassTag(BigRabbit.class, new Tag("!BigRabbit"));
        representer.addClassTag(RabbitPastureEventDto.class, new Tag("!RabbitPastureEvent"));
        return new Yaml(representer, options);
    }
}