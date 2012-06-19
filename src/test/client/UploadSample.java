package test.client;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Button;

public class UploadSample implements EntryPoint {

  
    
    public class MyFancyLookingSubmitButton extends Composite implements HasClickHandlers {
        DecoratorPanel widget = new DecoratorPanel();

        public MyFancyLookingSubmitButton() {
            Button widget = new Button("Submit");
            initWidget(widget);
        }

        public HandlerRegistration addClickHandler(ClickHandler handler) {
            return addDomHandler(handler, ClickEvent.getType());
        }
    }

    public void onModuleLoad() {

        SingleUploader uploader = new SingleUploader(null, new MyFancyLookingSubmitButton());
        uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
        RootPanel.get("root").add(uploader);

    }

    //Load the url in the document and in the case of success attach it to the viewer
    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
        public void onFinish(IUploader uploader) {
            if (uploader.getStatus() == Status.SUCCESS) {
                RootPanel.get("root").add(new Label(uploader.getFileName()));
            }
        }
    };
}
