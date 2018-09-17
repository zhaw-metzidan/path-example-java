import {NgModule} from '@angular/core';
import {PathExampleAppComponent} from "./path-example-app.component";
import {AppModule} from 'path-framework/app/app.module';

@NgModule({
    imports:      [AppModule],
    declarations: [PathExampleAppComponent],
    bootstrap:    [PathExampleAppComponent],
})
export class PathExampleAppModule {}