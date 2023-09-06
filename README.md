# 3semSP1
JPA Project
<img width="319" alt="Screenshot 2023-09-06 at 12 33 47" src="https://github.com/snadering/3semSP1/assets/113049401/1da48cbe-78c3-492d-9df4-2a4f13433161">


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

<h2>Brugere (Users):</h2>

<h4>Attributter:</h4>
First name: Brugerens fornavn.</br>
Surname: Brugerens efternavn.</br>
Phone number: Brugerens kontakttelefonnummer.</br>
Email: Brugerens e-mailadresse.</br>
<h4>Relationer:</h4>
Mange-til-mange med Hobby: Brugere kan have flere hobbyer, og hobbyer kan deles blandt flere brugere.</br>
Mange-til-mange med Begivenhed (Event): Brugere kan deltage i flere begivenheder, og begivenheder kan have flere brugere.</br>
Mange-til-én med Adresse: Brugere kan have én primær adresse.</br>

<h2>Hobby:</h2>

<h4>Attributter:</h4>
Name: Navnet på hobbyen.</br>
Category: Kategorien eller typen af hobbyen.</br>
<h4>Relationer:</h4>
Én-til-mange med Begivenhed (Event): En hobby kan være forbundet med flere begivenheder.</br>
Mange-til-mange med Brugere: Hobbyer kan deles blandt flere brugere.</br>

<h2>Begivenhed (Event):</h2>

<h4>Attributter:</h4>
Name: Navnet på begivenheden.</br>
Price: Omkostninger eller pris forbundet med at deltage i begivenheden.</br>
<h4>Relationer:</h4>
Mange-til-mange med Brugere: Begivenheder kan have flere deltagere (brugere).</br>
Mange-til-én med Adresse: Begivenheder er forbundet med én primær adresse.</br>
Mange-til-mange med Hobby: Begivenheder kan være relateret til flere hobbyer.</br>
