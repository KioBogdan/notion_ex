import React, { useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

const Register = () => {
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [jwt, setJwt] = useLocalState("", "jwt");
    function sendRegReq() {
        const reqBody = {
            firstname1: firstname,
            lastname1: lastname,
            username: username,
            email1: email,
            password1: password,
        }

        fetch("api/register", {
            headers: {
                "Content-Type": "application/json",
            }, 
            method: "POST",
            body: JSON.stringify(reqBody),
        })
        .then((response) => {
            if(response.status === 200) 
                return Promise.all([response.json(), response.headers]);
            else 
                return Promise.reject("Invalid register attempt");
        })
        .then(([body, headers]) => {
            setJwt(headers.get("authorization"));
            window.location.href = "financial";
        })
        .catch((message) => {
            alert(message);
        })
    }
    return (
        <>
        <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='firstname'>
                    <Form.Label className='fs-5'>First Name</Form.Label>
                    <Form.Control type="firstname" 
                            value={firstname} 
                            onChange={(event) => setFirstname(event.target.value)} placeholder="Your first name, e.g. Bill"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='lastname'>
                    <Form.Label className='fs-5'>Last Name</Form.Label>
                    <Form.Control type="lastname" 
                            value={lastname} 
                            onChange={(event) => setLastname(event.target.value)} placeholder="Last name, e.g. Joe"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='username'>
                    <Form.Label className='fs-5'>NickName</Form.Label>
                    <Form.Control type="username" 
                            value={username} 
                            onChange={(event) => setUsername(event.target.value)} placeholder="Choose a nickname that suits you"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='email'>
                    <Form.Label className='fs-5'>Email</Form.Label>
                    <Form.Control type="email" 
                            value={email} 
                            onChange={(event) => setEmail(event.target.value)} placeholder="Enter email"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='password'>
                    <Form.Label className='fs-5'>Password</Form.Label>
                    <Form.Control type="password" 
                            value={password} 
                            onChange={(event) => setPassword(event.target.value)} placeholder="Type in your password"
                            />
                </Form.Group>
                </Col>
            </Row>
                {/* <Form.Group className="mb-3" controlId='username'>
                    <Form.Label className='fs-5'>Email address</Form.Label>
                    <Form.Control type="email" 
                            value={username} 
                            onChange={(event) => setUsername(event.target.value)} placeholder="Enter email"/>
                </Form.Group> */}

                {/* <Form.Group className="mb-3" controlId='password'>
                    <Form.Label className='fs-5'>Password</Form.Label>
                    <Form.Control type="password" 
                            value={password} 
                            onChange={(event) => setPassword(event.target.value)} placeholder="Type in your password"/>
                </Form.Group> */}
            {/* </Form> */}

            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => sendRegReq()}
                        variant="secondary" >
                    Register
                </Button>

                </Col>
            </Row>
        </Container>
    </>
    );
};

export default Register;