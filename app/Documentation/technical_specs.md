## Technical Specifications

### Restrictions
Currently the **login is via twitter**, it may be that we add email/password later but version one is a twitter handle.

**Firebase** is the backend glue for this, it handles:
- Authentication
- Database
- Storage

**App** is written in _Kotlin_ the programming language of the cool intelligentsia.

#### Explicit Intents
- MainActivity - **1** Home Screen [*]
- DisasterActivity - **2** The main disaster status page [*]
- DisasterMapActivity - **3 ** Show where the disaster is on a google map [*]
- DisasterManagerActivity **5** Update/Merge/Close a disaster
- LoginPopUp **4** Twitter Login Screen
- LoggedInDisasterActivity **6** The clickable disaster list when logged in [*]
- CreateDisasterActivity **7** Create a new disaster
- DisasterOverviewActivity **8** Tabbed screen (Incidents/Twitter/Status) for a specified disaster














[Home](./Master_Document.md)
