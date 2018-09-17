import {Injectable} from "@angular/core";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service"

@Injectable()
export class PathExampleTranslationService extends TranslationService {

    public getSupportedLanguageCodes() : string[] {
        return ["en", "de", "it", "nl"];
    }

    protected getTranslation(key:string) : string {
        let myTranslations = this.createTranslationMap(this.getPathExampleTranslations());
        // prefer custom translations
        if (myTranslations.get(key) == null) {
            return super.getTranslation(key);
        }
        return myTranslations.get(key);
    }

    private getPathExampleTranslations() {
        let languageCode: string = this.getUserLanguage();

        // put additional application translations here
        if (languageCode == "de") {
            return {
                "Admin": "Admin",
                "AddPermissionRole": "Rolle hinzufügen",
                "BusinessCase": "Geschäftsfall",
                "BusinessCaseType": "Geschäftsfall-Typ",
                "BusinessCases": "Geschäftsfälle",
                "CreationDate": "Erstellungsdatum",
                "ClosingDate": "Gültig bis",
                "Comments": "Kommentar",
                "EditUser": "Benutzer bearbeiten",
                "EditBusinessCase": "Geschäftsfall bearbeiten",
                "EMail": "E-Mail",
                "FamilyName": "Nachname",
                "FirstName": "Vorname",
                "FinishDate": "Bearbeitungsdatum",
                "Manager": "Manager",
                "Name": "Name",
                "NewBusinessCase": "Neuer Geschäftsfall",
                "NewUser": "Neuer Benutzer",
                "Password": "Passwort",
                "PermissionRole": "Rolle",
                "Required": "Muss-Feld",
                "Reset": "Reset",
                "RepeatPassword": "Passwort wiederholen",
                "SearchVisible": "Suche sichtbar",
                "SearchRequired": "Suche notwendig",
                "SortOrder": "Reihenfolge",
                "Text": "Text",
                "Title": "Titel",
                "User": "Benutzer",
                "UserPermissionRole": "Benutzerrolle",
                "Users": "Benutzer",
                "Value": "Wert",
                "ValueDescription": "Wert-Beschreibung",
                "Width": "Breite"
            }
        } else {
            return {
                "Admin": "Admin",
                "BusinessCases": "Business Cases",
                "NotImplemented": "User account is not implemented",
                "Reset": "Reset",
            }
        }
    }

}