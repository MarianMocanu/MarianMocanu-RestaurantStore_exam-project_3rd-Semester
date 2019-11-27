package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Controller
public class EmailController {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    private void sendPreparedMail(String body, String sendTo, String subject) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNewOrderMail(String email, Order order) {
        //get and fill the template
        final Context context = new Context();
        context.setVariable("order", order);

        //set variables for the emails
        String body = templateEngine.process("mails/new-order", context);
        String emailSubject = "RestaurantStore | New Order";
        String sendTo = email;

        //send the html template
        sendPreparedMail(body, sendTo, emailSubject);
    }

    public void sendOrderProcessedMail(Order order){
        //get and fill the template
        final Context context = new Context();
        context.setVariable("order", order);

        //set variables for the emails
        String body = templateEngine.process("mails/new-order", context);
        String emailSubject = "RestaurantStore | Your order has been processed";
        String sendTo = order.getUser().getEmail();

        //send the html template
        sendPreparedMail(body, sendTo, emailSubject);
    }

    public void sendInvoiceMail(Order order){
        //get and fill the template
        final Context context = new Context();
        context.setVariable("order", order);

        //set variables for the emails
        String body = templateEngine.process("mails/new-order", context);
        String emailSubject = "RestaurantStore | Invoice order #" + order.getId();
        String sendTo = order.getUser().getEmail();

        //send the html template
        sendPreparedMail(body, sendTo, emailSubject);
    }
}
