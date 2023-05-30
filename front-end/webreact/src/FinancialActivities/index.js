import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';
import { Socket } from 'socket.io-client';
import { EmailIcon, EmailShareButton, FacebookIcon, FacebookShareButton, RedditIcon, RedditShareButton, TwitterIcon, TwitterShareButton } from 'react-share';
import { js2xml } from 'xml-js';

const FinancialActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    //const [param, setParam] = useState(null);
    const shareUrl = 'https://localhost:3000'; // URL to be shared
    const title = 'Still developping this site, but check it out!'; // Title of the shared content
    const [financials, setFinancials] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    
    const [file, setFile] = useState(); // setting file to import
    const [array, setArray] = useState([]);

    const fileReader = new FileReader();
    const [financial, setFinancial] = useState({
        expense: "", 
        amount: "",
        category: "",
        date: "",
    });

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        })
    }

    const handleOnChange = (e) => {
        setFile(e.target.files[0]);
    };

    const csvFileToArray = string => {
        const csvHeader = string.slice(0, string.indexOf("\n")).split(";");
        const csvRows = string.slice(string.indexOf("\r")+1).split("\r");

        const financialArray = csvRows.map(i => {
            const values = i.split(";");
            const obj = csvHeader.reduce((object, header, index) => {
                object[header] = values[index];
                return object;
            }, {});

            return obj;
        });

        // const financialArray = csvRows.map(i => {
        //     const values = i.split(";");

        //     const financialObj = {
        //         expense: values[1],
        //         amount: values[2],
        //         category: values[3],
        //         date: values[4],
        //     };

        //     console.log(financialObj);
        //     createFinancialParam(financialObj);
        //     return financialObj;

        // });
            
        console.log(financialArray);
        
        for(let i=0; i<= financialArray.length-2; i++) {
                // console.log(financialArray[i].expense);
                // updateFinancial("expense", financialArray[i].expense);
                // updateFinancial("amount", financialArray[i].amount);
                // updateFinancial("category", financialArray[i].category);
                // updateFinancial("date", financialArray[i].date);

                createFinancialParam(financialArray[i]);
        }
        //         console.log(financialArray[i]);
        //         // financial.expense = financialArray[i].expense;
        //         // financial.amount = financialArray[i].amount;
        //         // financial.category = financialArray[i].category;
        //         // financial.date = financialArray[i].date;
        //         //setFinancial(financialArray[i]); //= financialArray[i];
        //         //console.log(financialItem);
        //         //setFinancial(financialArray[i]);
        //         createFinancialParam(financial);
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();

        if(file) {
            fileReader.onload = function (event) {
                const csvOutput = event.target.result;
                csvFileToArray(csvOutput);
            };

            fileReader.readAsText(file);

            //console.log(fileReader);
        }
    };

    const exportAsXML = () => {
        const xmlData = js2xml(financials, { compact: true, ignoreComment: true, spaces: 4 });
        console.log(xmlData);
        
        const blob = new Blob([xmlData], { type: 'application/xml' });
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'data.xml';
        link.click();
      };

    const headerKeys = Object.keys(Object.assign({}, ...array));

    useEffect(() => {
        fetch("api/financial", {
            headers: {
                "Content-Type": "application/json",
                Authorization : `Bearer ${jwt}`,
            }, 
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((financialsData) => {
            setFinancials(financialsData);
        });

        window.addEventListener("scroll", () => {
            if(window.scrollY > 50)
                setBackToTopButton(true);
            else 
                setBackToTopButton(false);
        })
    }, [])

    function createFinancial() {
        fetch("api/financial", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(financial),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setFinancial(data);
            console.log(data);
        })
    }

    function createFinancialParam(param) {
        fetch("api/financial", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(param),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setFinancial(data);
            console.log(data);
        })
    }

    function fetchSorted(param) {
        fetch(`api/financial/sort/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setFinancials(data);
            
            console.log(data);
        })
    }

    function fetchSearch(param) {
        fetch(`api/financial/search/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setFinancials(data);
            console.log(data);
        })
    }

    function updateFinancial(prop, value){
        const newFinancial = { ...financial };
        newFinancial[prop] = value;
        setFinancial(newFinancial);
    }
    
 return (
    <Container>
      <Table striped bordered hover variant="dark">
        <thead>
            <tr>
            <th>#</th>
                <th>Expense</th>
                <th>Amount</th>
                <th>Category</th>
                <th>Date</th>
            </tr>
        </thead>
      <tbody>
        {financials ? financials.map((financial) => (
                    <tr>
                        <td>{financial.id}</td>
                        <td>{financial.expense}</td>
                        <td>{financial.amount}</td>
                        <td>{financial.category}</td>
                        <td>{financial.date}</td>
                    </tr> )) 
                :
                    array.map((item) => (
                        <tr>
                            {Object.values(item).map((val) => (
                                <td>{val}</td>
                            ))}
                        </tr>
                    ))
                // financials.map((financial) => (
                //     <tr>
                //         <td>{financial.id}</td>
                //         <td>{financial.expense}</td>
                //         <td>{financial.amount}</td>
                //         <td>{financial.category}</td>
                //         <td>{financial.date}</td>
                //     </tr> )) 
        }   
                {/* // <tr>
                //     <td>{array.id}</td>
                //     <td>{array.expense}</td>
                //     <td>{array.amount}</td>
                //     <td>{array.category}</td>
                //     <td>{array.date}</td>
                // </tr> */}
      </tbody>
     </Table>

       <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='expense'>
                    <Form.Label className='fs-5'>Financial Activity Name:</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateFinancial("expense", e.target.value)} 
                            value={financial.expense}
                            placeholder="Enter expense name"/>
                </Form.Group>
                </Col>

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='amount'>
                    <Form.Label className='fs-5'>Amount:</Form.Label>
                    <Form.Control type="number" 
                            onChange={(e) => updateFinancial("amount", e.target.value)} 
                            value={financial.amount} 
                            placeholder="Amount:"/>
                </Form.Group>
                </Col>

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='category'>
                    <Form.Label className='fs-5'>Category:</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateFinancial("category", e.target.value)} 
                            value={financial.category}
                            placeholder="Category of expense"/>
                </Form.Group>
                </Col>

                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='date'>
                    <Form.Label className='fs-5'>Date of expense:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateFinancial("date", e.target.value)} 
                            value={financial.date} 
                            placeholder="Date of financial:"/>
                </Form.Group>
                </Col>

                <Col>
                    <Button onClick={() => createFinancial()}> Add a financial task </Button>
                </Col>
            </Row>

            <Row>
            <Dropdown>
                    <Dropdown.Toggle variant="success" id="dropdown-basic">
                        Sort by:
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                        <Dropdown.Item  onClick={() => fetchSorted("expense")} >Expense</Dropdown.Item>
                        <Dropdown.Item  onClick={() => fetchSorted("amount")}>Amount</Dropdown.Item>
                        <Dropdown.Item  onClick={() => fetchSorted("category")}>Category</Dropdown.Item>
                        <Dropdown.Item  onClick={() => fetchSorted("date")}>Date</Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown>
            </Row>
        </Container>

        {backToTopButton && (
            <button style={
                {
                    position: "fixed",
                    bottom: "50px",
                    right: "50px",
                    height: "50px",
                    width: "50px",
                    fontSize: "50px",
                }}
                onClick={scrollUp}
            >
            </button>
        )}

    <Container> 
        <Row className='justify-content-center'>
            <Col md="8" lg="6">
                <Form className="mb-3" controlId='expense'>
                    <input 
                        type={"file"}
                        id={"csvFileInput"}
                        accept={".csv"}
                        onChange={handleOnChange}
                    />

                    <button 
                        onClick={(e) => {
                            handleOnSubmit(e);
                        }}
                    >
                        IMPORT CSV 
                    </button>
                </Form>
            </Col>
        </Row>
    </Container>
    <Container>
            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                <Form.Group className="mb-3" controlId='search'>
                    <Form.Label className='fs-5'>Search</Form.Label>
                    <Form.Control type="text" 
                            value={search} 
                            onChange={(event) => setSearch(event.target.value)} 
                            placeholder="search for a task"/>
                </Form.Group>

                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => fetchSearch(search)}
                        variant="dark" >
                    Search for
                </Button>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex gap-3 flex-md-row justify-content-between'>
                    <Button onClick={exportAsXML}>Export as XML</Button>
                </Col>
            </Row>
            
            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex gap-3 flex-md-row justify-content-between'>
                    <FacebookShareButton url={shareUrl} quote={title}>
                        <FacebookIcon logoFillColor="blue" />
                    </FacebookShareButton>

                    <TwitterShareButton url={shareUrl} title={title}>
                        <TwitterIcon logoFillColor="green" />
                    </TwitterShareButton>

                    <EmailShareButton url={shareUrl} title={title}>
                        <EmailIcon logoFillColor="mauve" />
                    </EmailShareButton>

                    <RedditShareButton url={shareUrl} title={title}>
                        <RedditIcon logoFillColor="orange" />
                    </RedditShareButton>
                </Col>
            </Row>
        </Container>
        <div>
            <table>
            <thead>
            <tr key={"header"}>
                {headerKeys.map((key) => (
                <th>{key}</th>
                ))}
            </tr>
            </thead>

            <tbody>
            {array.map((item) => (
                <tr key={item.id}>
                {Object.values(item).map((val) => (
                    <td>{val}</td>
                ))}
                </tr>
            ))}
            </tbody>
        </table>
        </div>
    </Container>
 );
};

export default FinancialActivities;