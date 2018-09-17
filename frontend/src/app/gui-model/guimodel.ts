export class GuiModel {

    private _guiModel = {
        "application": {
            "title": "Path Example Java",
            "formList": [
                {
                    "id": "OwnUserForm",
                    "title": "NotImplemented",
                    "formFieldList": [
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "UserForm",
                    "title": "User",
                    "url": "/user",
                    "formFieldList": [
                        {
                            "id":   "lastName",
                            "type": "text",
                            "name": "FamilyName",
                            "newRow": true,
                            "required": true
                        },
                        {
                            "id":   "firstName",
                            "type": "text",
                            "name": "FirstName",
                            "required": true
                        },
                        {
                            "id":   "email",
                            "type": "text",
                            "name": "EMail",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id":   "password",
                            "type": "text",
                            "name": "Password",
                            "newRow": true,
                            "width": 2,
                            "isPassword": true,
                            "required": true
                        },
                        {
                            "id":   "repeatPassword",
                            "type": "text",
                            "name": "RepeatPassword",
                            "newRow": true,
                            "width": 2,
                            "isPassword": true,
                            "required": true
                        },
                        {
                            "id": "evtCreationDate",
                            "type": "date",
                            "name": "CreationDate",
                            "required": true,
                            "newRow": true
                        },
                        {
                            "id": "evtClosingDate",
                            "type": "date",
                            "name": "ClosingDate"
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "newRow": true,
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
                        },
                        {
                            "type": "cancelButton",
                            "name": "Cancel"
                        },
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "UserPermissionRoleForm",
                    "title": "UserPermissionRole",
                    "url": "/user/:userKey/permissionRole",
                    "formFieldList": [
                        {
                            "id": "permissionRoleKey",
                            "type": "autocomplete",
                            "name": "PermissionRole",
                            "wordSearchEnabled": true,
                            "url": "/permissionRole",
                            "width": 2,
                            "required": true,
                            "readonly": true,
                        },
                        {
                            "id": "userKey",
                            "type": "autocomplete",
                            "name": "User",
                            "wordSearchEnabled": true,
                            "url": "/user",
                            "width": 2,
                            "form": "UserForm",
                            "required": true,
                            "readonly": true,
                            "defaultKey": "userKey"
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete",
                            "permissionUrl": "/permissionFunction/parameterCheck/:permissionRoleKey/:userKey",
                        },
                        {
                            "type": "cancelButton",
                            "name": "Cancel"
                        },
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "PermissionRoleForm",
                    "title": "PermissionRole",
                    "url": "/permissionRole",
                    "formFieldList": [
                        {
                            "id":   "name",
                            "type": "text",
                            "name": "PermissionRoleName",
                            "newRow": true,
                            "width": 2,
                            "required": true
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
                        },
                        {
                            "type": "cancelButton",
                            "name": "Cancel"
                        },
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "BusinessCaseForm",
                    "title": "BusinessCase",
                    "url": "/businessCase",
                    "formFieldList": [
                        {
                            "id": "name",
                            "type": "text",
                            "name": "Name",
                            "required": true,
                            "width": 2
                        },
                        {
                            "id": "title",
                            "type": "text",
                            "name": "Title",
                            "required": true,
                            "width": 2
                        },
                        {
                            "id": "manager",
                            "type": "autocomplete",
                            "name": "Manager",
                            "wordSearchEnabled": true,
                            "url": "/user",
                            "form": "UserForm",
                            "width": 1,
                            "required": true,
                            "readonly": true,
                            "defaultKey": "userKey"
                        },
                        {
                            "id": "businessCaseType",
                            "type": "autocomplete",
                            "name": "BusinessCaseType",
                            "wordSearchEnabled": true,
                            "url": "/businessCaseType",
                            "width": 1,
                            "required": true,
                            "readonly": true,
                            "defaultKey": "businessCaseTypeKey"
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "newRow": true,
                            "maxLength": 4000,
                            "height": 5,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
                        },
                        {
                            "type": "cancelButton",
                            "name": "Cancel"
                        },
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
            ],
            "pageList": [
                {
                    "id": "mainmenu",
                    "name": "MainMenu",
                    "elementList": [
                        {
                            "type": "button",
                            "name": "Users",
                            "icon": "fa-user",
                            "color": "carrot",
                            "width": 2,
                            "page": "userspage"
                        },
                        {
                            "type": "button",
                            "name": "BusinessCases",
                            "icon": "fa-cog",
                            "color": "sun-flower",
                            "width": 2,
                            "page": "businesscasespage"
                        }
                    ]
                },
                {
                    "id": "userspage",
                    "name": "Sub Page",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewUser",
                            "icon": "fa-user",
                            "color": "green",
                            "width": 2,
                            "form" : {
                                "form" : "UserForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "User",
                            "icon": "fa-user",
                            "color": "carrot",
                            "search": true,
                            "url": "/user",
                            "page": "userpage",
                        }
                    ]
                },
                {
                    "id": "userpage",
                    "name": "User",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "EditUser",
                            "icon": "fa-user",
                            "width": 2,
                            "color": "green",
                            "form": {
                                "form": "UserForm"
                            }
                        },
                        {
                            "type": "button",
                            "name": "AddPermissionRole",
                            "icon": "fa-check-circle",
                            "color": "green",
                            "width": 2,
                            "form": {
                                "form": "UserPermissionRoleForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Permission Role List",
                            "icon": "fa-check-circle",
                            "color": "belize hole",
                            "search": true,
                            "form": {
                                "form": "UserPermissionRoleForm"
                            },
                            "url": "/user/:userKey/permissionRole"
                        },
                    ]
                },
                {
                    "id": "businesscasespage",
                    "name": "Sub Page",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewBusinessCase",
                            "icon": "fa-cog",
                            "color": "green",
                            "width": 2,
                            "form" : {
                                "form" : "BusinessCaseForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "BusinessCase",
                            "icon": "fa-cog",
                            "color": "sun-flower",
                            "search": true,
                            "url": "/businessCase",
                            "form" : {
                                "form" : "BusinessCaseForm"
                            }
                        }
                    ]
                }
            ]
        }
    };


    get guiModel() {
        return this._guiModel;
    }
}