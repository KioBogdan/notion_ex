import React from 'react';

import { ShareButtons, FacebookShareButton, TwitterShareButton, FacebookIcon, TwitterIcon } from 'react-share';
//const { FacebookShareButton, TwitterShareButton } = ShareButtons;
const Homepage = () => {
    const shareUrl = 'https://localhost:3000'; // URL to be shared
    const title = 'Still developping this site, but check it out!'; // Title of the shared content
    return (
        <div>
            <FacebookShareButton url={shareUrl} quote={title}>
                <FacebookIcon logoFillColor="blue" />
            </FacebookShareButton>

            <TwitterShareButton url={shareUrl} title={title}>
                <TwitterIcon logoFillColor="green" />
            </TwitterShareButton>
        </div>
    );
};

export default Homepage;