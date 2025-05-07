import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import './smallbanner.css'
import { useTranslation } from 'react-i18next'


const SmallBanner = () => {
  const { t } = useTranslation()

  return (
    <div className='div-with-background border mt-5'>
      <Container>
        <Row className='quote-container mt-5 me-1'>
          <h1>{t('smallBanner.quote')}</h1>
          <p>{t('smallBanner.author')}</p>
        </Row>
      </Container>
    </div>
  )
}

export default SmallBanner
