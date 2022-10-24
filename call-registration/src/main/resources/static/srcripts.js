const API_BASE_URL = "http://localhost:8080";

const submitCall = async (name, lastName, email, dateOfBirth) => {
    await fetch(`${API_BASE_URL}/call-registration`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({name, lastName, email, dateOfBirth})
    });
    await renderCallRegistrationTable();
}

const getAllCalls = async () => {
    const response = await fetch(`${API_BASE_URL}/call-registration`);
    const calls = await response.json();
    return calls;
}

const handleCallRegistrationFormSubmit = async () => {
    const form = document.getElementById("callForm");
    form.addEventListener("submit", (e)=>{
        e.preventDefault();
        submitCall(
            form.name.value, 
            form.lastName.value, 
            form.email.value, 
            form.dateOfBirth.value
        );
        getAllCalls();
    });
};

const renderCallRegistrationTable = async () => {
    const calls = await getAllCalls();
    if (document.getElementById("callRegistrationTable")) {
        document.getElementById("callRegistrationTable").remove();
    }
    const table = document.createElement("table");
    table.id = "callRegistrationTable";
    renderCallRegistrationTableHeaders (table)
    calls.forEach((call) => {
        renderCallRegistrationTableRow(table, call)
    });
    document.getElementById("callRegistrationContainer").appendChild(table);
}

const renderTableData = (innerText) => {
    const td = document.createElement("td");
    if (innerText) {
        td.innerText = innerText;
    }
    return td;
}

const renderTableHeader = (innerText) => {
    const th = document.createElement("th");
    th.innerText = innerText;
    return th;
}

const renderActionButton = (actionsTd, id) => {
    const editButton = document.createElement("button");
    editButton.setAttribute("class", "btn");
    editButton.innerText = "Koreguoti";
    editButton.addEventListener("click", () => {
    })

    const deleteButton = document.createElement("button");
    deleteButton.setAttribute("class", "btn");
    deleteButton.innerText = "Ištrinti";
    deleteButton.addEventListener("click", () => {
        handleDelete(id);
    })

    actionsTd.append(editButton, deleteButton)
}


const handleDelete = async (id) => {
    await fetch(`${API_BASE_URL}/call-registration/${id}`, {
        method: 'DELETE',
    });
    await renderCallRegistrationTable();
        
};

const renderCallRegistrationTableRow = (table, call) => {
    const tr = document.createElement("tr");
    const nameTd = renderTableData(call.name);
    const lastNameTd = renderTableData(call.lastName);   
    const emailTd = renderTableData(call.email);
    const dateOfBirthTd = renderTableData (call.dateOfBirth);
    const actionsTd = renderTableData();

    renderActionButton(actionsTd, call.id);

    tr.append(nameTd, lastNameTd, emailTd, dateOfBirthTd, actionsTd);
    table.appendChild(tr);
}

const renderCallRegistrationTableHeaders = (table) => {
    const tr = document.createElement("tr");
    const nameTh = renderTableHeader("Vardas");
    const lastNameTh = renderTableHeader("Pavardė");
    const emailTh = renderTableHeader("El. pašto adresas");
    const dateOfBirthTh = renderTableHeader("Gimimo data");
    const actionsTh = renderTableHeader("Veiksmai");
   
    tr.append(nameTh, lastNameTh, emailTh, dateOfBirthTh, actionsTh);
    table.appendChild(tr);
}

(async () => {
    await renderCallRegistrationTable();
    await handleCallRegistrationFormSubmit();
})();

