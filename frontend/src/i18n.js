import i18n from 'i18next'
import { initReactI18next } from 'react-i18next'
import LanguageDetector from 'i18next-browser-languagedetector'

// Import translation files
import translationEN from './locales/en/translation.json'
import translationHI from './locales/hi/translation.json'
import translationMR from './locales/mr/translation.json'

// Language resources
const resources = {
  en: {
    translation: translationEN
  },
  hi: {
    translation: translationHI
  },
  mr: {
    translation: translationMR
  }
}

i18n
  .use(LanguageDetector) // auto-detect user's preferred language
  .use(initReactI18next) // connect with react
  .init({
    resources,
    fallbackLng: 'en',
    interpolation: {
      escapeValue: false // React already escapes
    },
    detection: {
      order: ['localStorage', 'navigator'],
      caches: ['localStorage']
    }
  })

export default i18n
