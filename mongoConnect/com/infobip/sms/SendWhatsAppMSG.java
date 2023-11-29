package com.infobip.sms;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.WhatsAppApi;
import com.infobip.model.*;

/**
 * Send WhatsApp template message by using Infobip API Java Client.
 *
 * THIS CODE EXAMPLE IS READY BY DEFAULT. HIT RUN TO SEND THE MESSAGE!
 *
 * Send WhatsApp API reference: <a href="https://www.infobip.com/docs/api#channels/whatsapp/send-whatsapp-template-message">...</a>
 * See Readme file for details.
 */

public class SendWhatsAppMSG {
    private static final String BASE_URL = "https://e1dlgq.api.infobip.com";
    private static final String API_KEY = "b40faa0d2e7a08ce4eb41067b589e1e6-e4685a16-26a5-420a-b412-12324caafb6f";

    private static final String SENDER = "447860099299";
    private static final String RECIPIENT = "923014384681";

    public static void main(String[] args) {
        // Create the API client and the Whatsapp API instances.
        ApiClient apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY))
                .withBaseUrl(BaseUrl.from(BASE_URL))
                .build();
        WhatsAppApi whatsAppApi = new WhatsAppApi(apiClient);


        // Create the content of the message.
        WhatsAppTemplateContent content = new WhatsAppTemplateContent()
                .language("en")
                .templateName("registration_success")
                .templateData(new WhatsAppTemplateDataContent()
                        .header(new WhatsAppTemplateDocumentHeaderContent()
                                .mediaUrl("https://api.infobip.com/ott/1/media/infobipLogo")
                                .filename("infobipLogo")
                        )
                        .body(new WhatsAppTemplateBodyContent()
                                .addPlaceholdersItem("Fuzail")
                                .addPlaceholdersItem("WhatsApp message")
                                .addPlaceholdersItem("delivered")
                                .addPlaceholdersItem("exploring Infobip API")
                        )
                        .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                .parameter("Yes"))
                        .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                .parameter("No"))
                        .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                .parameter("Later"))
                );

        // Create a message to send and add to bulk.
        WhatsAppMessage message = new WhatsAppMessage()
                .from(SENDER)
                .to(RECIPIENT)
                .content(content);
        WhatsAppBulkMessage bulkMessage = new WhatsAppBulkMessage()
                .addMessagesItem(message);


        try {
            // Send message.
            WhatsAppBulkMessageInfo whatsAppBulkMessageInfo = whatsAppApi.sendWhatsAppTemplateMessage(bulkMessage).execute();
            System.out.println("Response body: " + whatsAppBulkMessageInfo);
        } catch (ApiException e) {
            System.out.println("HTTP status code: " + e.responseStatusCode());
            System.out.println("Response body: " + e.rawResponseBody());
        }
    }
}
