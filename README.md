# 3semSP1
<h1>JPA Project</h1>
<p"> af Bastian Holm, Christian Stennicke, Sander Roust, Tobias Berg og Tobias Christiansen</p>
<h2>Business Idea</h2>
Hobby Buddy: Focus on organizing and promoting hobby-related events and gatherings in various cities. Users can input their hobbies and preferences, and the platform suggests events and meetups they might enjoy. Revenue could come from event ticket sales, event promotion fees, or affiliate marketing. </br>

<h2>User Stories</h2>
[US-1] - As a user I want to get all the information about a person. </br>
[US-2] - As a user I want to get all phone numbers from a given person. </br>
[US-3] - As a user I want to get all persons with a given hobby. </br>
[US-4] - As a user I want to get the number of people with a given hobby. </br>
[US-5] - As a user I want to get a list all hobbies + a count of how many are interested in each hobby. </br>
[US-6] As a user I want to get all persons living in a given city (i.e. 2625 Vallensbæk). </br>
[US-7] As a user I want to get a list of all postcodes and city names in Denmark. </br>
[US-8] As a user I want to get all the information about a person (address, hobbies etc) given a phone number. </br>
[US-9] As a user I want to be able to do CRUD operations on all JPA entities unless it wouldn't make sense for a given entity. </br>
[US-10] Funktion til at hente alle event i en given tidsperiode. </br>
[US-11] Funktion til at hente alle events relevante til given hobby. </br>


<h2>Domænemodel</h2>
<h3>Postnummer (Zipcode): </h3>

<h4>Attributter:</h4>
Zip: Postnummeret.</br>
City: Byen tilknyttet postnummeret.</br>
<h4>Relationer:</h4>
Én-til-mange med Adresse: Et postnummer kan være forbundet med flere adresser.</br>

<h3>Adresse (Address):</h3>

<h4>Attributter:</h4>
Street: Gadenavnet på adressen.</br>
Number: Husetnummeret på adressen.</br>
<h4>Relationer:</h4>
Én-til-mange med Begivenhed (Event): En adresse kan være forbundet med flere begivenheder.</br>
Én-til-mange med Brugere (Users): En adresse kan være forbundet med flere brugere.</br>
Én-til-én med Postnummer (Zipcode): En adresse har et forhold til ét postnummer.</br>

<h3>Brugere (Users):</h3>

<h4>Attributter:</h4>
First name: Brugerens fornavn.</br>
Surname: Brugerens efternavn.</br>
Phone number: Brugerens kontakttelefonnummer.</br>
Email: Brugerens e-mailadresse.</br>
<h4>Relationer:</h4>
Mange-til-mange med Hobby: Brugere kan have flere hobbyer, og hobbyer kan deles blandt flere brugere.</br>
Mange-til-mange med Begivenhed (Event): Brugere kan deltage i flere begivenheder, og begivenheder kan have flere brugere.</br>
Mange-til-én med Adresse: Brugere kan have én primær adresse.</br>

<h3>Hobby:</h3>

<h4>Attributter:</h4>
Name: Navnet på hobbyen.</br>
Category: Kategorien eller typen af hobbyen.</br>
<h4>Relationer:</h4>
Én-til-mange med Begivenhed (Event): En hobby kan være forbundet med flere begivenheder.</br>
Mange-til-mange med Brugere: Hobbyer kan deles blandt flere brugere.</br>

<h3>Begivenhed (Event):</h3>

<h4>Attributter:</h4>
Name: Navnet på begivenheden.</br>
Price: Omkostninger eller pris forbundet med at deltage i begivenheden.</br>
<h4>Relationer:</h4>
Mange-til-mange med Brugere: Begivenheder kan have flere deltagere (brugere).</br>
Mange-til-én med Adresse: Begivenheder er forbundet med én primær adresse.</br>
Mange-til-mange med Hobby: Begivenheder kan være relateret til flere hobbyer.</br>
</br>
<h3>Billede af domænemodel</h3>
<img width="319" alt="Screenshot 2023-09-06 at 12 33 47" src="https://github.com/snadering/3semSP1/assets/113049401/1da48cbe-78c3-492d-9df4-2a4f13433161">
</br>

<h2>EER-Diagram</h2>

<h4>Zipcode:</h4>
Primærnøgle (PK): zip </br>
Attributter: region_name (regionsnavn), city_name (bynavn), municipality_name (kommunenavn) </br>
Gemmer information om postnumre og deres tilhørende region, by og kommune. </br>

<h4>Address:</h4>
Primærnøgle (PK): id </br>
Attributter: street (gade), number (nummer) </br>
Fremmednøgle (FK): zip (refererer til zip-tabellen) </br>
Indeholder data relateret til fysiske adresser med en reference til postnumre. </br>

<h4>Hobby:</h4> 
Primærnøgle (PK): id </br>
Attributter: name (navn), category (kategori), wikilink (wikilink), type (type) </br>
Representerer forskellige hobbyer eller interesser, kategoriseret efter navn og kategori og med links til yderligere ressourcer. </br>

<h4>Event:</h4>
Primærnøgle (PK): id </br>
Attributter: name (navn), price (pris) </br>
Fremmednøgle (FK): address (refererer til address-tabellen), hobby (refererer til hobby-tabellen) </br>
Gemmer information om arrangementer, herunder deres navne, priser og steder med links til adresser og tilknyttede hobbyer. </br>

<h4>Users:</h4>
Primærnøgle (PK): id </br>
Attributter: name (navn), surname (efternavn), phone_number (telefonnummer), email (e-mail) </br>
Fremmednøgle (FK): address (refererer til address-tabellen)
Indeholder brugeroplysninger såsom navne, kontaktoplysninger og links til deres adresser. </br>

<h4>Event_Users:</h4>
Fremmedenøgler (FK): event_id (refererer til event-tabellen), users_id (refererer til users-tabellen)
Representerer den mange-til-mange-relation mellem arrangementer og brugere og angiver, hvilke brugere der deltager i hvilke arrangementer. </br>

<h4>Hobby_Users:</h4>
Fremmedenøgler (FK): hobby_id (refererer til hobby-tabellen), users_id (refererer til users-tabellen)
Representerer den mange-til-mange-relation mellem hobbyer og brugere og viser, hvilke hobbyer hver bruger er interesseret i. </br>
<h3>Billede af EER-diagram</h3>
<img width="560" alt="Screenshot 2023-09-06 at 12 37 55" src="https://github.com/snadering/3semSP1/assets/113049401/b91095c7-50bb-4f84-81fa-873109d95554">

