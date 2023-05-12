import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';

const FinancialActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    //const [param, setParam] = useState(null);
    const [financials, setFinancials] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
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
                </tr> )) : <></>
        }
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
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='amount'>
                    <Form.Label className='fs-5'>Amount:</Form.Label>
                    <Form.Control type="number" 
                            onChange={(e) => updateFinancial("amount", e.target.value)} 
                            value={financial.amount} 
                            placeholder="Amount:"/>
                </Form.Group>
                </Col>
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='category'>
                    <Form.Label className='fs-5'>Category:</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateFinancial("category", e.target.value)} 
                            value={financial.category}
                            placeholder="Category of expense"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='date'>
                    <Form.Label className='fs-5'>Date of expense:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateFinancial("date", e.target.value)} 
                            value={financial.date} 
                            placeholder="Date of financial:"/>
                </Form.Group>
                </Col>
            </Row>
        </Container>
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
        <button onClick={() => createFinancial()}> Add a financial task </button>
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
          )
        }
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
        </Container>
     </Container>
  );

    // return (
        
    //     <div style={{ margin: "2em" }}>
    //         {financials ? financials.map((financial) => (
    //             <div>Financial Activity ID: {financial.id}</div>)) : <></>}
    //         <button onClick={() => createFinancial()}> Add a financial task </button>
    //     </div>
    // );
};

export default FinancialActivities;