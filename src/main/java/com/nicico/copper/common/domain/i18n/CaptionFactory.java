//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nicico.copper.common.domain.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CaptionFactory {
    private static MessageSource messageSource;

    public CaptionFactory(MessageSource messageSource) {
        CaptionFactory.messageSource = messageSource;
    }

    public static String getLabel(String word) {
        try {
            return messageSource.getMessage(word, (Object[])null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException var2) {
            return word;
        }
    }
}
