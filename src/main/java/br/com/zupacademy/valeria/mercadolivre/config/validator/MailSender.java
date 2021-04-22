package br.com.zupacademy.valeria.mercadolivre.config.validator;

import java.util.logging.Logger;

public class MailSender {

    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String USERNAME = "google@gmail.com";
    private static final String PASSWORD = "google123";

    private String from;
    private String to;
    private String subject;
    private String text;

    public MailSender send() {
        var logger = Logger.getLogger("global");

        logger.info("From: " + from);
        logger.info("To: " + to);
        logger.info("Subject: " + subject);
        logger.info("Text: " + text);

        logger.info("Email enviado com sucesso!");

        return this;
    }

    public MailSender setFrom(String from) {
        this.from = from;
        return this;
    }

    public MailSender setTo(String to) {
        this.to = to;
        return this;
    }

    public MailSender setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailSender setText(String text) {
        this.text = text;
        return this;
    }
}
