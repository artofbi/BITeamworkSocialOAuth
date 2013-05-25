/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artofbi.biteamworksocialoauth;

import java.util.Scanner;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.builder.api.YammerApi;
import org.scribe.builder.api.YammerApi2;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.Preconditions;

/**
 *
 * @author CS <christian@artofbi.com>
 */
public class App {

    private static String PROTECTED_RESOURCE_URL = "https://www.yammer.com/api/v1/messages.json";
    private static final Token EMPTY_TOKEN = null;
    
    
    public App() {
        
    }
    
    public static void main(String[] args) {
        
        //BITeamworkSocialCLI.testParsingSFDCFeeds();
        
        //testParsing();
        
        //BITeamworkSocialCLI.yammerTokenExtractor();
        
        App.testTwitter();
        
        //BITeamworkSocialCLI.testYammer2();
        
    }
    
    
    public static void testParsingSFDCFeeds(){
        String rawResponse = "{'items':[{'parent':{'name':'All Fyght Group, Inc.','owner':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'description':'Get company announcements and other important updates','visibility':'PublicAccess','memberCount':2,'fileCount':0,'community':null,'canHaveChatterGuests':false,'mySubscription':{'id':'0FBC0000000QW2VOAW','url':'/services/data/v26.0/chatter/group-memberships/0FBC0000000QW2VOAW'},'myRole':'GroupOwner','photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D4SF/F','photoVersionId':'729C0000000D4SFIA0','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D4SF/T'},'lastFeedItemPostDate':'2013-01-09T16:39:48.000Z','id':'0F9C0000000PJmlKAG','url':'/services/data/v26.0/chatter/groups/0F9C0000000PJmlKAG','type':'CollaborationGroup'},'id':'0D5C000000v9xprKAA','type':'LinkPost','clientInfo':{'applicationName':'BITeamwork Chatter','applicationUrl':'http://www.biteamwork.com'},'url':'/services/data/v26.0/chatter/feed-items/0D5C000000v9xprKAA','createdDate':'2013-01-09T16:39:48.000Z','body':{'text':'Link to the dashboard.','messageSegments':[{'type':'Text','text':'Link to the dashboard.'}]},'event':false,'visibility':'AllUsers','modifiedDate':'2013-01-09T16:39:48.000Z','photoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T','comments':{'total':0,'comments':[],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/feed-items/0D5C000000v9xprKAA/comments'},'likes':{'total':0,'likes':[],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/feed-items/0D5C000000v9xprKAA/likes','previousPageUrl':null},'isBookmarkedByCurrentUser':false,'isDeleteRestricted':false,'isLikedByCurrentUser':false,'myLike':null,'actor':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'attachment':{'titlex':'View it Here','url':'http://ad-vna5daekwmiq:9704/analytics/saw.dll?Dashboard&Page=Overview'},'originalFeedItem':null,'originalFeedItemActor':null,'topics':null},{'parent':{'name':'All Fyght Group, Inc.','owner':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'description':'Get company announcements and other important updates','visibility':'PublicAccess','memberCount':2,'fileCount':0,'community':null,'canHaveChatterGuests':false,'mySubscription':{'id':'0FBC0000000QW2VOAW','url':'/services/data/v26.0/chatter/group-memberships/0FBC0000000QW2VOAW'},'myRole':'GroupOwner','photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D4SF/F','photoVersionId':'729C0000000D4SFIA0','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D4SF/T'},'lastFeedItemPostDate':'2013-01-09T16:39:48.000Z','id':'0F9C0000000PJmlKAG','url':'/services/data/v26.0/chatter/groups/0F9C0000000PJmlKAG','type':'CollaborationGroup'},'id':'0D5C000000uITeSKAW','type':'TextPost','clientInfo':null,'url':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW','createdDate':'2012-12-31T02:32:58.000Z','body':{'text':'Here&#39;s a quick comment to all of the company under the main group.','messageSegments':[{'type':'Text','text':'Here&#39;s a quick comment to all of the company under the main group.'}]},'event':false,'visibility':'AllUsers','modifiedDate':'2013-01-06T02:33:54.000Z','photoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Cy/T','comments':{'total':12,'comments':[{'parent':{'id':'0F9C0000000PJmlKAG','url':'/services/data/v26.0/chatter/groups/0F9C0000000PJmlKAG'},'id':'0D7C00000007s51KAA','type':'TextComment','clientInfo':{'applicationName':'BITeamwork Chatter','applicationUrl':'http://www.biteamwork.com'},'url':'/services/data/v26.0/chatter/comments/0D7C00000007s51KAA','createdDate':'2013-01-05T22:54:56.000Z','body':{'text':'give me one more.','messageSegments':[{'type':'Text','text':'give me one more.'}]},'user':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'likes':{'total':0,'likes':[],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/comments/0D7C00000007s51KAA/likes','previousPageUrl':null},'isDeleteRestricted':false,'myLike':null,'attachment':null,'feedItem':{'id':'0D5C000000uITeSKAW','url':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW'}},{'parent':{'id':'0F9C0000000PJmlKAG','url':'/services/data/v26.0/chatter/groups/0F9C0000000PJmlKAG'},'id':'0D7C00000007s56KAA','type':'TextComment','clientInfo':{'applicationName':'BITeamwork Chatter','applicationUrl':'http://www.biteamwork.com'},'url':'/services/data/v26.0/chatter/comments/0D7C00000007s56KAA','createdDate':'2013-01-05T22:54:56.000Z','body':{'text':'give me one more.','messageSegments':[{'type':'Text','text':'give me one more.'}]},'user':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'likes':{'total':0,'likes':[],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/comments/0D7C00000007s56KAA/likes','previousPageUrl':null},'isDeleteRestricted':false,'myLike':null,'attachment':null,'feedItem':{'id':'0D5C000000uITeSKAW','url':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW'}},{'parent':{'id':'0F9C0000000PJmlKAG','url':'/services/data/v26.0/chatter/groups/0F9C0000000PJmlKAG'},'id':'0D7C00000007s5BKAQ','type':'TextComment','clientInfo':{'applicationName':'BITeamwork Chatter','applicationUrl':'http://www.biteamwork.com'},'url':'/services/data/v26.0/chatter/comments/0D7C00000007s5BKAQ','createdDate':'2013-01-05T22:54:56.000Z','body':{'text':'give me one more.','messageSegments':[{'type':'Text','text':'give me one more.'}]},'user':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'},'likes':{'total':0,'likes':[],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/comments/0D7C00000007s5BKAQ/likes','previousPageUrl':null},'isDeleteRestricted':false,'myLike':null,'attachment':null,'feedItem':{'id':'0D5C000000uITeSKAW','url':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW'}}],'nextPageUrl':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW/comments?page=2013-01-05T22%3A54%3A56Z%2C0D7C00000007s51KAA','currentPageUrl':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW/comments'},'likes':{'total':1,'likes':[{'id':'0I0C0000000EhiYKAS','url':'/services/data/v26.0/chatter/likes/0I0C0000000EhiYKAS','user':{'name':'Christian Screen','title':null,'userType':'Internal','firstName':'Christian','lastName':'Screen','companyName':'Fyght Group, Inc.','isActive':true,'mySubscription':null,'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/F','photoVersionId':'729C0000000D7PiIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Pi/T'},'id':'00580000001sSYUAA2','url':'/services/data/v26.0/chatter/users/00580000001sSYUAA2','type':'User'}}],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/feed-items/0D5C000000uITeSKAW/likes','previousPageUrl':null},'isBookmarkedByCurrentUser':false,'isDeleteRestricted':false,'isLikedByCurrentUser':true,'myLike':{'id':'0I0C0000000EhiYKAS','url':'/services/data/v26.0/chatter/likes/0I0C0000000EhiYKAS'},'actor':{'name':'Christian ArtOfBI','title':null,'userType':'Internal','firstName':'Christian','lastName':'ArtOfBI','companyName':'ArtOfBI&gt;com','isActive':true,'mySubscription':{'id':'0E880000000AFOvCAO','url':'/services/data/v26.0/chatter/subscriptions/0E880000000AFOvCAO'},'photo':{'largePhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Cy/F','photoVersionId':'729C0000000D7CyIAK','smallPhotoUrl':'https://c.na8.content.force.com/profilephoto/729C0000000D7Cy/T'},'id':'00580000003BKrPAAW','url':'/services/data/v26.0/chatter/users/00580000003BKrPAAW','type':'User'},'attachment':null,'originalFeedItem':null,'originalFeedItemActor':null,'topics':null}],'nextPageUrl':null,'currentPageUrl':'/services/data/v26.0/chatter/feeds/record/0F9C0000000PJmlKAG/feed-items?sort=LastModifiedDateDesc','isModifiedUrl':null}";
        

        //Preconditions.checkEmptyString(rawResponse, "Cannot extract a token from a null or empty String");
        
        //return new Token("", "", "");
        
        try {

            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree( rawResponse.replace("'", "\"") );


//            System.out.println( rootNode.findPath("id").getTextValue() );
//            System.out.println( rootNode.findValue("type").getTextValue() );
//            System.out.println( rootNode.findPath("url").getTextValue() );
//            System.out.println( rootNode.findPath("createdDate").getTextValue() );
            JsonNode rootElem = rootNode.get("items");
            for(JsonNode arrayElem : rootElem ){

//                System.out.println( arrayElem.findPath("body").get("text") + " : " 
//                        + arrayElem.findPath("createdDate") );
//                System.out.println( arrayElem.findPath("user").get("name") );
//                System.out.println( "---" + arrayElem.findPath("actor").get("photo").get("smallPhotoUrl").getTextValue() );
//                System.out.println( arrayElem.findPath("likes").get("total") );
//                System.out.println( "---" + arrayElem.get("url") );
//                System.out.println( "---" + arrayElem.get("isLikedByCurrentUser").getBooleanValue() );
                
                String htmlOut = "";
                if( !arrayElem.get("attachment").isNull() ){
                    //System.out.println("going for unlike");
                    if(arrayElem.get("attachment").has("url") && arrayElem.get("attachment").has("title")){
                        htmlOut += "<br /><img src='/bitw/images/icons/chain.png' style='border:none;' />&nbsp;<span class='twSideBarSocialAttachmentLink'><a href='" + arrayElem.get("attachment").get("url").getTextValue() + "' target='_blank' action='goAttachment'>" + arrayElem.get("attachment").get("title").getTextValue() + "</a></span>";
                    }
                }
                System.out.println( "Link : " + htmlOut );
                
                // Date Formatting
//                Date now = new Date();
//                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                dateParser.setTimeZone(TimeZone.getTimeZone("Z"));
//                String markDate = arrayElem.findPath("createdDate").getTextValue();
//                String createdDate = "?";
//                Long timeLapse = now.getTime() - dateParser.parse(markDate).getTime();
//                System.out.println("Time Lapsed: " + timeLapse);
//                // One day = 24*60, 1440
//                if (timeLapse >= 1440 * 60 * 1000) {
//                    createdDate = DateFormat.getInstance().format(dateParser.parse(markDate)).toString();
//                    System.out.println("Created Date: " +  createdDate );
//                } else {
//                    createdDate = TimeUtils.formatMillis(timeLapse, TimeUnit.HOURS, TimeUnit.MINUTES, true);
//                    System.out.println("Time : " + TimeUtils.formatMillis(timeLapse, TimeUnit.HOURS, TimeUnit.MINUTES, true));
//                }
               
                
                //if(arrayElem.get("isLikedByCurrentUser").getBooleanValue()){
                    //System.out.println("**********  TRUE ************");
//                    System.out.println("******* myLike :" +  arrayElem.getPath("myLike").isNull());
                    
                //}
                
//                System.out.println( "---comment id: " + arrayElem.get("id") );
//                
//                SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                dateParser.setTimeZone(TimeZone.getTimeZone("Z"));
//                String markDate = arrayElem.findPath("createdDate").getTextValue();
//                System.out.println( markDate + " ...to... " + DateFormat.getInstance().format(dateParser.parse(markDate)) );
//                
//                
//                JsonNode commentsElem = arrayElem.findPath("comments").findPath("comments");
//                
//                for(JsonNode commentElem : commentsElem ){
//                    System.out.println( "---" + commentElem.findPath("type") );
//                    System.out.println( "---" + commentElem.findPath("body").get("text") );
//                    System.out.println( "---" + commentElem.findPath("user").get("name") );
//                    System.out.println( "---" + commentElem.findPath("photo").get("smallPhotoUrl") );
//                    System.out.println( "---" + commentElem.findPath("createdDate") );
//                    System.out.println( "---" + commentElem.findPath("likes").get("total") );
//                    System.out.println( "---reply id: " + commentElem.get("id") );
//                }
  
            }
            
            
//            System.out.println( rootNode.findPath("id").getTextValue() );
//            System.out.println( rootNode.findValue("type").getTextValue() );
//            System.out.println( rootNode.findPath("url").getTextValue() );
//            System.out.println( rootNode.findPath("createdDate").getTextValue() );
////            JsonNode commentElem = rootNode.get("parent");
//            JsonNode parentElem = rootNode.findPath("parent");
//            //System.out.println( parentElem.toString() );
//            System.out.println( rootNode.findPath("body").get("text") );
//            
//            JsonNode commentElem = rootNode.findPath("comments").findPath("comments");
//            //System.out.println( commentElem.toString() );
//            
//            //htmlOut += rootNode.get("display_name").getTextValue();
//            for(JsonNode arrayElem : commentElem ){
//                System.out.println( arrayElem.findPath("body").get("text") );
//                System.out.println( arrayElem.findPath("user").get("name") );
//                System.out.println( arrayElem.findPath("likes").get("total") );
//                System.out.println( "---" + arrayElem.get("url") );
//                //System.out.println( arrayElem.get("id").getTextValue() + " : " + arrayElem.get("name").getTextValue() );
//            }
            

        } catch (Exception ex) {
            System.out.println("Excpetion in What?");
            System.out.println( ex.getMessage() );

        }
    }
    
    
    
    public static void yammerTokenExtractor(){
        
        
        String yammerResponse = "{'user':{'external_urls':[],'summary':'Guy #1 for new collaboration tool plugin, myyammer.','settings':{'xdr_proxy':'https://xdrproxy.yammer.com'},'name':'christian','web_url':'https://www.yammer.com/myyammer.com/users/christian','full_name':'Christian','birth_date':'','activated_at':'2012/10/09 15:48:47 +0000','expertise':'','network_id':474692,'mugshot_url':'https://mug0.assets-yammer.com/mugshot/images/48x48/no_photo.png','interests':'','stats':{'followers':3,'following':3,'updates':4},'state':'active','type':'user','significant_other':'','admin':'false','mugshot_url_template':'https://mug0.assets-yammer.com/mugshot/images/{width}x{height}/no_photo.png','job_title':'Guy #1','network_domains':['myyammer.com'],'guid':null,'schools':[],'url':'https://www.yammer.com/api/v1/users/1489964501','kids_names':'','hire_date':null,'timezone':'Pacific Time (US \u0026 Canada)','network_name':'myyammer.com','verified_admin':'false','show_ask_for_photo':true,'first_name':'Christian','location':'Charlotte, NC','last_name':'dude','contact':{'phone_numbers':[{'number':'9999999','type':'work'}],'email_addresses':[{'type':'primary','address':'christian@myyammer.com'}],'im':{'provider':'aim','username':''}},'department':'myyammer Yammer Testing','id':1489964501,'can_broadcast':'false','previous_companies':[]},'network':{'paid':false,'moderated':false,'permalink':'myyammer.com','name':'myyammer.com','is_group_enabled':true,'web_url':'https://www.yammer.com/myyammer.com','header_background_color':'#396B9A','navigation_text_color':'#FFFFFF','show_upgrade_banner':false,'type':'network','profile_fields_config':{'enable_work_phone':true,'enable_job_title':true,'enable_mobile_phone':true},'navigation_background_color':'#38699F','community':false,'is_chat_enabled':true,'created_at':'2012/10/09 15:34:48 +0000','is_org_chart_enabled':true,'id':474692,'header_text_color':'#FFFFFF'},'access_token':{'modify_subscriptions':true,'modify_messages':true,'authorized_at':'2012/11/18 14:27:52 +0000','view_messages':true,'view_subscriptions':true,'network_id':474692,'view_tags':true,'view_groups':true,'view_members':true,'user_id':1489964501,'token':'IOnl00dCCYmnKBRbcCwrrQ','network_name':'myyammer.com','network_permalink':'myyammer.com','expires_at':null,'created_at':'2012/11/18 14:27:52 +0000'}}";
        

//        String apiKey = "AFLbtUhSMaQrKEjTDzvg";
//                String apiSecret = "q7G7J1wXQluZGqpI0cDWUiiXsIZttpRP3f7RUW8uk";
//                OAuthService service = new ServiceBuilder()
//                        .provider(YammerApi2.class)
//                        .apiKey(apiKey)
//                        .apiSecret(apiSecret)
//                        .callback("http://teamworkbar.dev/bitw/x/testyammercallback/1")
//                        .build();
                
        YammerApi2 yp2 = new YammerApi2();
        
        System.out.println ( yp2.getAccessTokenExtractor().getClass().toString() );
        
        Token strHope = yp2.getAccessTokenExtractor().extract( yammerResponse.replace("'", "\"")  );
        
        System.out.println( strHope );
        
        
    }
    

    
    
    public static void testParsing()
    {
        String yammerResponse = "{'user':{'external_urls':[],'summary':'Guy #1 for new collaboration tool plugin, myyammer.','settings':{'xdr_proxy':'https://xdrproxy.yammer.com'},'name':'christian','web_url':'https://www.yammer.com/myyammer.com/users/christian','full_name':'Christian','birth_date':'','activated_at':'2012/10/09 15:48:47 +0000','expertise':'','network_id':474692,'mugshot_url':'https://mug0.assets-yammer.com/mugshot/images/48x48/no_photo.png','interests':'','stats':{'followers':3,'following':3,'updates':4},'state':'active','type':'user','significant_other':'','admin':'false','mugshot_url_template':'https://mug0.assets-yammer.com/mugshot/images/{width}x{height}/no_photo.png','job_title':'Guy #1','network_domains':['myyammer.com'],'guid':null,'schools':[],'url':'https://www.yammer.com/api/v1/users/1489964501','kids_names':'','hire_date':null,'timezone':'Pacific Time (US \u0026 Canada)','network_name':'myyammer.com','verified_admin':'false','show_ask_for_photo':true,'first_name':'Christian','location':'Charlotte, NC','last_name':'dude','contact':{'phone_numbers':[{'number':'9999999','type':'work'}],'email_addresses':[{'type':'primary','address':'christian@myyammer.com'}],'im':{'provider':'aim','username':''}},'department':'myyammer Yammer Testing','id':1489964501,'can_broadcast':'false','previous_companies':[]},'network':{'paid':false,'moderated':false,'permalink':'myyammer.com','name':'myyammer.com','is_group_enabled':true,'web_url':'https://www.yammer.com/myyammer.com','header_background_color':'#396B9A','navigation_text_color':'#FFFFFF','show_upgrade_banner':false,'type':'network','profile_fields_config':{'enable_work_phone':true,'enable_job_title':true,'enable_mobile_phone':true},'navigation_background_color':'#38699F','community':false,'is_chat_enabled':true,'created_at':'2012/10/09 15:34:48 +0000','is_org_chart_enabled':true,'id':474692,'header_text_color':'#FFFFFF'},'access_token':{'modify_subscriptions':true,'modify_messages':true,'authorized_at':'2012/11/18 14:27:52 +0000','view_messages':true,'view_subscriptions':true,'network_id':474692,'view_tags':true,'view_groups':true,'view_members':true,'user_id':1489964501,'token':'IOnl00dCCYmnKBRbcCwrrQ','network_name':'myyammer.com','network_permalink':'myyammer.com','expires_at':null,'created_at':'2012/11/18 14:27:52 +0000'}}";
        

        Preconditions.checkEmptyString(yammerResponse, "Cannot extract a token from a null or empty String");
        
        //return new Token("", "", "");
        
        try {

            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree( yammerResponse.replace("'", "\"") );
            
            JsonNode address = actualObj.get("access_token");
            String tokenOut = address.get("token").toString();

            System.out.println( tokenOut );
            
            //String access_token_hit = SECOND_SINGLE.getValue(json);
            //System.out.println( JSON_FORMATTER.format(json) );
            //System.out.println( access_token_hit );
            //String jsonOut = json.getStringValue("token", 0);
            //System.out.println(jsonOut);
            
        } catch (Exception ex) {
            System.out.println("Excpetion in What?");
            System.out.println( ex.getMessage() );

        }
    }

    
    public static void testYammer2() {


        String apiKey = "AFLbtUhSMaQrKEjTDzvg";
        String apiSecret = "q7G7J1wXQluZGqpI0cDWUiiXsIZttpRP3f7RUW8uk";
        OAuthService service = new ServiceBuilder()
                .provider(YammerApi2.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("http://teamworkbar.dev/bitw/x/testyammercallback")
                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Yammer2's OAuth Workflow ===");
        System.out.println();



        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize Scribe here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken + " )");



        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL
                + accessToken.getToken());

        System.out.println("Here is the request being sent:");
        System.out.println(oauthRequest.toString());

        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();

        System.out.println("Got it! Lets see what we found...");
        System.out.println();

        // the oauthResponse.getCode() is really important because we need to look for a 400 error which sould
        // tell us that we need to the client to reissue the workflow to get an access token.
        // Or 429 which is Too Many Requests
        System.out.println(oauthResponse.getCode());


        System.out.println(oauthResponse.getBody());





    }


    public static void testYammer() {
        
        OAuthService service = new ServiceBuilder()
                .provider(YammerApi.class)
                .apiKey("AFLbtUhSMaQrKEjTDzvg")
                .apiSecret("q7G7J1wXQluZGqpI0cDWUiiXsIZttpRP3f7RUW8uk")
                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Yammers's OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");
        System.out.println();

        System.out.println("Now go and authorize Scribe here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken + " )");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        //request.addBodyParameter("status", "this is sparta! *");
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
    }

    public static void testTwitter() {
        
        PROTECTED_RESOURCE_URL = "https://api.twitter.com/1/statuses/sample.json";
        
        
        // Set Scribe to leverage the system based proxy out to the vendor URL
        // Ref: http://docs.oracle.com/javase/6/docs/technotes/guides/net/proxies.html
        // Ref: https://github.com/fernandezpablo85/scribe-java/wiki/faq
        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("http.proxyHost", "192.168.1.12");
        System.setProperty("http.proxyPort", "8081");

        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey("7XAAl3JRBrEnSBe6cjw")
                .apiSecret("pjKDO1PNzGlIFZ0bvc1SRQhuptFrqY1RdaD1a178")
                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Twitter's OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!" );
        System.out.println("Token : " + requestToken.getToken().toString() );
        System.out.println("Secret " + requestToken.getSecret().toString() );
        System.out.println( requestToken.getRawResponse().toString() );
        System.out.println();

        System.out.println("Now go and authorize Scribe here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken + " )");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        //request.addBodyParameter("status", "this is sparta! *");
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
    }
}