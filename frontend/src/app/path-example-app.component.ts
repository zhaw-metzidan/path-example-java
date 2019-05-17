/* angular/path imports */
import {Component} from "@angular/core";

/* model imports */
import {GuiModel} from "./gui-model/guimodel";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";
import {PathExampleTranslationService} from "./path-example-translation-service";
import {PathService} from "path-framework/app/path-framework/service/path.service";
import {PathAppComponent} from "path-framework/app/path-framework/path-app.component";

@Component({
    selector: "path-application",
    templateUrl: "./../../node_modules/path-framework/app/path-framework/path-app.component.html",
    // providers: [{ provide: path.PathService, useClass: path.PathMockService }]
    providers: [PathService, { provide: TranslationService, useClass: PathExampleTranslationService }]
})
export class PathExampleAppComponent extends PathAppComponent {

    private _appConfig = new GuiModel();

    constructor(pathService: PathService, translationService: TranslationService) {
        super(pathService, translationService);
    }

    protected getFrontendVersion(): string {
        return "0.0.6-SNAPSHOT";
    }

    protected getStartPage(): string {
        return "mainmenu";
    }

    protected getApplicationLogo(): string {
        return null;
    }

    protected getOwnUserForm(): string {
        return "OwnUserForm";
    }

    protected getGuiModel() {
        if (this._appConfig != null) {
            return this._appConfig.guiModel;
        }
        return null;
    }

    public getBackendUrl() {
        if (window.location.hostname.indexOf("localhost") !== -1) {
            return "http://localhost:4567/services";
        }
        let url: string = window.location.href;
        url = url.replace("/#", "");
        if (url.endsWith("/")) {
            return url + "services";
        }
        return url + "/services";
    }

    protected getBeans() {
        return {};
    }

    protected getHandlers() {
        return {};
    }
}
