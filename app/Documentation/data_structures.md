# Data Structures

#### Version **0.1** 12/5/2017

### Disaster
**Disaster** the top level parent for the data structures. One disaster many incidents. A **disaster** is opened by a volunteer. The only configurable part is the TwitterHashTagString which is a comma separated string of # tags to monitor.

Field | Comment
--- | ---
ID  |
Longitude |
Latitude |
Name |
TwitterHashTagString | **_comma separated string of twitter hash tags._**
Status | Open, Resolved, Closed

### Incident
Many **incidents** make a disaster. These are open by volunteers as needed. Each incident is assigned to a specific disaster.

**Status** : Open, Resolved, Closed

Field | Comment
--- | ---
ID | Unique incident ID
Disaster ID | Parent Disaster ID
Longitude |
Latitude |
Address |
Status | Open, Resolved, Closed
Resources | # of resources required

### Volunteer

**Volunteers** login with their twitter handle, which also serves as a unique ID. They can put in their name, or it will be retrieved from twitter (_Still thinking about this_). I would like each person to have a photo, not sure if that will work. **Status** will be used to check availability.

**_Roles_**:
- Initiator : the person who initiated the disaster :-)
- General Manager : They will be able to reassign, and manage people and incidents at will. They are created by either an Initiator or another GM
- Incident Manager : If you create an incident, your the manage until _you_ transfer that role.
- ~~On site resource : When you commit to an incident you become the onsite resource until you leave.~~
- Volunteer : Everyone else is a volunteer

Field | Comment
--- | ---
Twitter Handle | UID
Name |
Photo | Required
Role  | See above
Availability | Boolean

### Incident Log

**Logging** facility  for the disaster.

**Activity**
- Created : Incident Created
- Joined : Volunteer joined incident
- Left : Volunteer left incident
- Resolved
- Closed
- Transferred : Incident manager transferred to twitter handle


Field | Comment
--- | ---
ID | UID for record
DateTime | Time of Activity
Incident ID | Incident UID
Twitter Handle | Volunteer
Activity |



[Home](./Master_Document.md)
