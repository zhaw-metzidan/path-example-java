import {Injectable} from "@angular/core";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";

@Injectable()
export class PathExampleTranslationService extends TranslationService {

    public getSupportedLanguageCodes(): string[] {
        return ["en", "de", "it", "nl"];
    }

    protected getTranslation(key: string): string {
        const myTranslations = this.createTranslationMap(this.getPathExampleTranslations());
        // prefer custom translations
        if (myTranslations.get(key) == null) {
            return super.getTranslation(key);
        }
        return myTranslations.get(key);
    }

    private getPathExampleTranslations() {
        const languageCode: string = this.getUserLanguage();

        // put additional application translations here
        if (languageCode === "de") {
            return {
                "Admin": "Admin",
                "AddPermissionRole": "Rolle hinzufügen",
                "BusinessCase": "Modul",
                "BusinessCaseType": "Modul-Typ",
                "BusinessCases": "ModulTafel",
                "BusinessCase2": "Geschäftsfall",
                "BusinessCaseType2": "Geschäftsfall-Typ",
                "BusinessCases2": "Geschäftsfälle",
                "Modul-Name": "Modul-Name",
                "Modul-id": "Modul-kürzel",
                "Modul-description": "Modul Beschreibung",
                "Modul": "Modul",
                "ModulType": "ModulTyp",
                "ModulTafel": "ModulTafel",
                "CreationDate": "Erstellungsdatum",
                "NewModul": "Neues Modul",
                "ClosingDate": "Gültig bis",
                "Comments": "Kommentar",
                "EditUser": "Benutzer bearbeiten",
                "EditBusinessCase": "Modul bearbeiten",
                "EMail": "E-Mail",
                "FamilyName": "Nachname",
                "FirstName": "Vorname",
                "FinishDate": "Bearbeitungsdatum",
                "Manager": "Manager",
                "Name": "Name",
                "NewBusinessCase": "Neues Modul",
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
                "Title": "Modukürzel",
                "User": "Benutzer",
                "UserPermissionRole": "Benutzerrolle",
                "Users": "Benutzer",
                "Value": "Wert",
                "ValueDescription": "Wert-Beschreibung",
                "Width": "Breite"
            };
        } else {
            return {
                "Admin": "Admin",
                "BusinessCases": "Business Cases",
                "Modul": "Modul",
                "NotImplemented": "User account is not implemented",
                "Reset": "Reset",
            };
        }
    }

}
