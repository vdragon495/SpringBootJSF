package ru.technolab.demo.jsf;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GenericBean<T> {
	
	private T selected;
	
	private List<T> model;

    public T getSelected() {
		return selected;
	}

	public void setSelected(T selected) {
		this.selected = selected;
	}

	public List<T> getModel() {
		return model;
	}

	protected void setModel(List<T> model) {
		this.model = model;
	}

	protected void addSavingStatusMessage(boolean res) {
        if ( res )
            showMsg( "Success", "Данные сохранены" );
        else
            showMsg( "Error", "Ошибка при сохранении данных" );
    }

    protected void showMsg(FacesMessage.Severity severity, String summary, String text) {
        FacesMessage msg = new FacesMessage(summary, text);
        msg.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    protected void showMsg( String summary, String text ) {
        switch (summary) {
            case "Error":
                showMsg( FacesMessage.SEVERITY_ERROR, "Ошибка", text );
                break;
            case "Warning":
                showMsg( FacesMessage.SEVERITY_WARN, "Предупреждение", text );
                break;
            default:
                showMsg( FacesMessage.SEVERITY_INFO, summary, text );
                break;
        }
    }
}
