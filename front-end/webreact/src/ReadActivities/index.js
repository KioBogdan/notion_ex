import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';

const ReadActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [reads, setReads] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    const [read, setRead] = useState({
        name: "",
        author: "",
        type: "",
        status: "",
        score: "",
        dateOfCompletion: "",
    })

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        })
    }

    useEffect(() => {
        fetch("api/read", {
            headers: {
                "Content-Type": "application/json",
                Authorization : `Bearer ${jwt}`,
            }, 
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((readsData) => {
            setReads(readsData);
        });

        window.addEventListener("scroll", () => {
            if(window.scrollY > 50)
                setBackToTopButton(true);
            else 
                setBackToTopButton(false);
        })
    }, [])

    function createRead() {
        fetch("api/read", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(read),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setRead(data);
            console.log(data);
        })
    }

    function fetchSorted(param) {
        fetch(`api/read/sort/${param}`, {
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
            setReads(data);
            console.log(data);
        })
    }

    function fetchSearch(param) {
        fetch(`api/read/search/${param}`, {
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
            setReads(data);
            console.log(data);
        })
    }

    function updateRead(prop, value) {
        const newRead = { ...read };
        newRead[prop] = value;
        setRead(newRead);
    }

    return (
     <Container>
        <Table striped bordered hover variant="dark">
            <thead>
                <tr>
                <th>#</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Score</th>
                    <th>Completed in</th>
                </tr>
            </thead>
          <tbody>
            {reads ? reads.map((read) => (
                    <tr>
                        <td>{read.id}</td>
                        <td>{read.name}</td>
                        <td>{read.author}</td>
                        <td>{read.type}</td>
                        <td>{read.status}</td>
                        <td>{read.score}</td>
                        <td>{read.dateOfCompletion}</td>
                    </tr> )) : <></>
            }
          </tbody>
        </Table>
          
          <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='name'>
                    <Form.Label className='fs-5'>Name of the book:</Form.Label>
                    <Form.Control type="text"  
                            onChange={(e) => updateRead("name", e.target.value)} 
                            value={read.name}
                            placeholder="Enter book name"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='author'>
                    <Form.Label className='fs-5'>Author Name</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateRead("author", e.target.value)} 
                            value={read.author}
                            placeholder="Enter author name"/>
                </Form.Group>
                </Col>
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='type'>
                    <Form.Label className='fs-5'>Type</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateRead("type", e.target.value)} 
                            value={read.type} 
                            placeholder="Book type:"/>
                </Form.Group>
                </Col>
            </Row>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='status'>
                    <Form.Label className='fs-5'>Status of reading:</Form.Label>
                    <Form.Control type="text" 
                            onChange={(e) => updateRead("status", e.target.value)} 
                            value={read.status}
                            placeholder="Enter status: "/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='score'>
                    <Form.Label className='fs-5'>Score</Form.Label>
                    <Form.Control type="number" 
                            onChange={(e) => updateRead("score", e.target.value)} 
                            value={read.score} 
                            placeholder="Rating"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='dateOfCompletion'>
                    <Form.Label className='fs-5'>Finished at:</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateRead("dateOfCompletion", e.target.value)} 
                            value={read.dateOfCompletion} 
                            placeholder="Date of completion"/>
                </Form.Group>
                </Col>
            </Row>
        </Container>
        <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort by:
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item  onClick={() => fetchSorted("name")} >Name</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("author")}>Author</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("type")}>Type</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("status")}>Status</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("score")}>Score</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("dateOfCompletion")}>Date</Dropdown.Item>
                </Dropdown.Menu>
        </Dropdown>
        <button onClick={() => createRead()}> Add a read task </button>
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
    //         {reads ? reads.map((read) => (
    //             <div>Read Activity ID: {read.id}</div>)) : <></>}
    //         <button onClick={() => createRead()}> Add a read task </button>
    //     </div>
    // );
};

export default ReadActivities;